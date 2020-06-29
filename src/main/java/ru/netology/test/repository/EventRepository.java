package ru.netology.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netology.test.model.Event;

import java.util.Date;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByNameLike(String name);

//    List<Event> findByDateGreaterThan(Date date);
}
