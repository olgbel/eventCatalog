package ru.netology.test.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.test.init.EventNotFoundException;
import ru.netology.test.model.Event;
import ru.netology.test.service.EventService;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/events/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable(value = "id") Long eventId) {
        Event event = eventService.getEventById(eventId)
                .orElseThrow(() -> new EventNotFoundException(eventId));
        return ResponseEntity.ok().body(event);
    }

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok().body(events);
    }

    @GetMapping("/events/name/{eventName}")
    public ResponseEntity<List<Event>> getAllEventsByName(@PathVariable String eventName) {
        List<Event> events = eventService.getEventsByName(eventName);
        return ResponseEntity.ok().body(events);
    }

    @GetMapping("/events/later/{eventDate}")
    public ResponseEntity<List<Event>> getEventByDate(@PathVariable String eventDate) {
        List<Event> events = eventService.getEventByDate(eventDate);
        return ResponseEntity.ok().body(events);
    }

    @PostMapping("/events")
    public Event createEvent(@RequestBody Event event) {
        return eventService.insertOrUpdateEvent(event);
    }

    @PutMapping("/events/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable(value = "id") Long eventId, @RequestBody Event eventDetails) {
        Event event = eventService.getEventById(eventId)
                .orElseThrow(() -> new EventNotFoundException(eventId));
        event.setName(eventDetails.getName());
        event.setDate(eventDetails.getDate());
        event.setType(eventDetails.getType());
        final Event updatedUser = eventService.insertOrUpdateEvent(event);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/event/{id}")
    public ResponseEntity deleteEvent(@PathVariable(value = "id") Long eventId) {
        Event event = eventService.getEventById(eventId)
                .orElseThrow(() -> new EventNotFoundException(eventId));
        eventService.deleteEvent(event);
        return ResponseEntity.accepted().build();
    }
}
