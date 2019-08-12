package com.prezyk.patient_data_server.data;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "EVENT")
public class Event {

    public Event(){}

    public Event(Patient patient, String eventType, Date date, String description) {
        this.patient = patient;
        this.eventType = eventType;
        this.date = date;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @ManyToOne
    @JoinColumn
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Patient patient;

    @Column(name = "EVENT_TYPE")
    private String eventType;

    @Column(name = "DATE", nullable = false)
    private Date date;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;


    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
