package ru.netology.test.service;

import org.springframework.stereotype.Service;
import ru.netology.test.model.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EventServiceImpl implements EventService {

    private static final Map<Long, Event> eventRepositoryMap = new HashMap<>();
    private static final AtomicLong eventId = new AtomicLong();

    @Override
    public void createEvent(Event event) {
        final long clientId = eventId.incrementAndGet();
        event.setId(clientId);
        eventRepositoryMap.put(clientId, event);
    }

    @Override
    public Event updateEvent(Event event, long id) {
        if (eventRepositoryMap.containsKey(id)) {
            event.setId(id);
            eventRepositoryMap.put(id, event);
            return eventRepositoryMap.get(id);
        }
        return null;
    }

    @Override
    public Event getEvent(long id) {
        return eventRepositoryMap.get(id);
    }

    @Override
    public List<Event> getAllEvents() {
        return new ArrayList<>(eventRepositoryMap.values());    }

    @Override
    public boolean removeEvent(long id) {
        return eventRepositoryMap.remove(id) != null;
    }
}
