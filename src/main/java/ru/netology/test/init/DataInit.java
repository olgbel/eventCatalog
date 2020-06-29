package ru.netology.test.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.test.repository.EventRepository;
import ru.netology.test.model.Event;
import ru.netology.test.model.EventType;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

//@Configuration
@Slf4j
public class DataInit {
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

//    @Bean
    public void run(EventRepository eventRepository) {
//        try {
//            log.info("Preloading " + eventRepository.save(new Event(1, "testMovie", EventType.MOVIE, df.parse("2020-09-12"))));
//            log.info("Preloading " + eventRepository.save(new Event(2, "testConcert", EventType.CONCERT, df.parse("1999-22-12"))));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    }
}
