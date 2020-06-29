package ru.netology.test.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id", nullable = false)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

//    @Column(name = "event_type", nullable = false)
//    @Enumerated(EnumType.STRING)
//    private EventType type;
//
//    @Temporal(value = TemporalType.DATE)
//    @Column(name = "event_date", nullable = false)
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date date;

    public Event(long id, String name/*, EventType type, Date date*/) {
        this.id = id;
        this.name = name;
//        this.type = type;
//        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public EventType getType() {
//        return type;
//    }
//
//    public void setType(EventType type) {
//        this.type = type;
//    }
//
//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name/*, type, date*/);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (this.getClass() != obj.getClass())
            return false;

        Event objB = (Event) obj;
        return this.id == objB.getId() &&
                this.name.equals(objB.getName())/* &&
                this.type == objB.getType() &&
                this.date == objB.getDate()*/;
    }

    @Override
    public String toString() {
        return "Event {" +
                "id = " + id + '\'' +
                "name = " + name + '\'' + /*
                "type = " + type + '\'' +
                "date = " + date + */
                "}";
    }
}
