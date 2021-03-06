package ru.netology.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.netology.test.model.Event;

import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findEventsByNameIgnoreCaseContaining(String name);

    List<Event> findEventsByDateAfterOrderByDate(Date date);
}
