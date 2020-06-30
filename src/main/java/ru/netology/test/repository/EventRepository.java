package ru.netology.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.netology.test.model.Event;

import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query(value = "select * from events where lower(name) like '%' || lower(:eventName) || '%'", nativeQuery = true)
    List<Event> findByNameLike(@Param("eventName") String name);

    @Query(value = "select * from events where date > :date", nativeQuery = true)
    List<Event> findByDateGreaterThan(Date date);
}
