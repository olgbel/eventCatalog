package ru.netology.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.netology.test.model.Event;
import ru.netology.test.model.EventType;
import ru.netology.test.service.EventService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;

@Slf4j
@WebMvcTest(controllers = EventController.class)
@ActiveProfiles("test")
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Event> eventList;

    @BeforeEach
    void setUp() {
        eventList = new ArrayList<>();
        Event movie = new Event();
        movie.setName("Home Alone");
        movie.setType(EventType.MOVIE);
        movie.setDate(new Date());
        eventList.add(movie);
    }

    @Test
    void getEventById() throws Exception {
        Long eventId = 1L;
        Event event = new Event();
        event.setName("Alai Oli");
        event.setType(EventType.CONCERT);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        event.setDate(formatter.parse("2020-12-12"));

        given(eventService.getEventById(eventId)).willReturn(Optional.of(event));

        mockMvc.perform(get("/api/v1/events/{id}", eventId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(event.getId()), Long.class))
                .andExpect(jsonPath("$.name", is(event.getName())))
                .andExpect(jsonPath("$.type", is(event.getType().toString())));
    }

    @Test
    void getAllEvents() throws Exception {
        given(eventService.getAllEvents()).willReturn(eventList);
        mockMvc.perform(get("/api/v1/events"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(eventList.size())));
    }

    @Test
    void shouldReturn404WhenFindEventById() throws Exception {
        final Long eventId = 99L;
        given(eventService.getEventById(eventId)).willReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/events/{id}", eventId))
                .andExpect(status().isNotFound());
    }

    @Test
    void getEventByDate() {
    }

    @Test
    void createEvent() throws Exception {
        given(eventService.insertOrUpdateEvent(any(Event.class))).willAnswer((invocation) -> invocation.getArgument(0));

        Event event = new Event();
        event.setName("Die Hard");
        event.setType(EventType.MOVIE);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        event.setDate(formatter.parse("1988-07-12"));

        mockMvc.perform(post("/api/v1/events")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(event)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(event.getName())))
                .andExpect(jsonPath("$.type", is(event.getType().toString())));
    }

    @Test
    void updateEvent() throws Exception {
        Event event = new Event();
        event.setName("Romeo and Juliet");
        event.setType(EventType.THEATER);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        event.setDate(formatter.parse("2015-08-30"));

        given(eventService.getEventById(event.getId())).willReturn(Optional.of(event));
        given(eventService.insertOrUpdateEvent(any(Event.class))).willAnswer((invocation) -> invocation.getArgument(0));

        Event updatedEvent = new Event();
        updatedEvent.setType(EventType.MOVIE);
        this.mockMvc.perform(put("/api/v1/events/{id}", event.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(updatedEvent)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(event.getName())))
                .andExpect(jsonPath("$.type", is(event.getType().toString())));
    }

    @Test
    void deleteEvent() throws Exception {
        Event event = new Event();
        event.setName("Romeo and Juliet");
        event.setType(EventType.THEATER);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        event.setDate(formatter.parse("2015-08-30"));

        given(eventService.getEventById(event.getId())).willReturn(Optional.of(event));
        doNothing().when(eventService).deleteEvent(event);

        this.mockMvc.perform(delete("/api/v1/event/{id}", event.getId()))
                .andExpect(status().isAccepted());
    }
}