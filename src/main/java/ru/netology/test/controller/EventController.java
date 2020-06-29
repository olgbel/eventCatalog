package ru.netology.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.test.model.Event;
import ru.netology.test.service.EventService;

import java.util.List;

@RestController
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService clientService) {
        this.eventService = clientService;
    }

    @PostMapping(value = "/events")
    public ResponseEntity<?> createEvent(@RequestBody Event event) {
        eventService.createEvent(event);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/events")
    public ResponseEntity<List<Event>> getAllEvents() {
        final List<Event> events = eventService.getAllEvents();

        return events != null &&  !events.isEmpty()
                ? new ResponseEntity<>(events, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/events/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable(name = "id") int id) {
        final Event event = eventService.getEvent(id);

        return event != null
                ? new ResponseEntity<>(event, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/events/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable(name = "id") int id, @RequestBody Event event) {
        final Event updated = eventService.updateEvent(event, id);

        return updated != null
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/events/{id}")
    public ResponseEntity<?> removeEvent(@PathVariable(name = "id") int id) {
        final boolean deleted = eventService.removeEvent(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
