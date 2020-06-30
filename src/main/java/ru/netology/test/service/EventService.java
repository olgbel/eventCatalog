package ru.netology.test.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.netology.test.model.Event;
import ru.netology.test.repository.EventRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> getEventsByName(String eventName) {
        return eventRepository.findEventsByNameIgnoreCaseContaining(eventName);
    }

    public List<Event> getEventByDate(String eventDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dt;
        try {
            dt = formatter.parse(eventDate);
        } catch (ParseException e) {
            log.error("Cannot parse the event date: {}", eventDate);
            throw new RuntimeException(e.getMessage());
        }
        return eventRepository.findEventsByDateAfterOrderByDate(dt);
    }

    public Event insertOrUpdateEvent(Event event) {
        return eventRepository.save(event);
    }

    public void deleteEvent(Event event) {
        eventRepository.delete(event);
    }
}
