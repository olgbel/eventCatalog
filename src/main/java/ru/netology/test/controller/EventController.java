package ru.netology.test.controller;

import org.springframework.web.bind.annotation.*;
import ru.netology.test.init.EventNotFoundException;
import ru.netology.test.model.Event;
import ru.netology.test.repository.EventRepository;

import java.util.List;

@RestController
public class EventController {
    private final EventRepository repository;

    public EventController(EventRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/events")
    List<Event> all() {
        return repository.findAll();
    }

    @PostMapping("/events")
    Event newEvent(@RequestBody Event newEvent) {
        return repository.save(newEvent);
    }

    @GetMapping("/events/{id}")
    Event one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
    }

    @PutMapping("/events/{id}")
    Event replaceEmployee(@RequestBody Event newEvent, @PathVariable Long id) {
        return repository.findById(id)
                .map(event -> {
                    event.setName(newEvent.getName());
//                    event.setType(newEvent.getType());
//                    event.setDate(newEvent.getDate());
                    return repository.save(event);
                })
                .orElseGet(() -> {
                    newEvent.setId(id);
                    return repository.save(newEvent);
                });
    }

    @DeleteMapping("/events/{id}")
    void deleteEvent(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
