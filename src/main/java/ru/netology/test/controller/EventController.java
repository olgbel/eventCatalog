package ru.netology.test.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.test.init.EventNotFoundException;
import ru.netology.test.model.Event;
import ru.netology.test.repository.EventRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class EventController {
    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/events/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable(value = "id") Long eventId) {
        Event event = eventRepository
                .findById(eventId)
                .orElseThrow(() -> new EventNotFoundException(eventId));
        return ResponseEntity.ok().body(event);
    }

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return ResponseEntity.ok().body(events);
    }

    @PostMapping("/events")
    Event createEvent(@RequestBody Event event) {
        if (event.getDate() == null) {
            event.setDate(new Date());
        }
        return eventRepository.save(event);
    }

    @PutMapping("/events/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable(value = "id") Long eventId, @RequestBody Event eventDetails) {
        Event event = eventRepository
                .findById(eventId)
                .orElseThrow(() -> new EventNotFoundException(eventId));
        event.setName(eventDetails.getName());
        event.setDate(eventDetails.getDate());
        event.setType(eventDetails.getType());
        final Event updatedUser = eventRepository.save(event);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/event/{id}")
    public Map<String, Boolean> deleteEvent(@PathVariable(value = "id") Long eventId) {
        Event event =
                eventRepository
                        .findById(eventId)
                        .orElseThrow(() -> new EventNotFoundException(eventId));
        eventRepository.delete(event);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
