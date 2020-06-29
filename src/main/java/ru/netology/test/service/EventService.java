package ru.netology.test.service;

import ru.netology.test.model.Event;

import java.util.List;

public interface EventService {
    void createEvent(Event event);

    Event updateEvent(Event event, long id);

    Event getEvent(long id);

    List<Event> getAllEvents();

    boolean removeEvent(long id);
}
