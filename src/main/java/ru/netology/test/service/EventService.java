package ru.netology.test.service;

import ru.netology.test.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    Optional<Event> getEventById(Long id);

    List<Event> getAllEvents();

    List<Event> getEventsByName(String eventName);

    List<Event> getEventByDate(String eventDate);

    Event save(Event event);

    void deleteEvent(Event event);
}
