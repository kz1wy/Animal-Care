package com.animalcare.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "feeding_schedules")
public class FeedingSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id")
    private ZooAnimal animal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enclosure_id")
    private Enclosure enclosure;

    @Column(name = "feeding_time", nullable = false)
    private LocalTime feedingTime;

    @Column(name = "feeding_notes", length = 1000)
    private String feedingNotes;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ZooAnimal getAnimal() {
        return animal;
    }

    public void setAnimal(ZooAnimal animal) {
        this.animal = animal;
    }

    public Enclosure getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(Enclosure enclosure) {
        this.enclosure = enclosure;
    }

    public LocalTime getFeedingTime() {
        return feedingTime;
    }

    public void setFeedingTime(LocalTime feedingTime) {
        this.feedingTime = feedingTime;
    }

    public String getFeedingNotes() {
        return feedingNotes;
    }

    public void setFeedingNotes(String feedingNotes) {
        this.feedingNotes = feedingNotes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Constructor, getters and setters
}
