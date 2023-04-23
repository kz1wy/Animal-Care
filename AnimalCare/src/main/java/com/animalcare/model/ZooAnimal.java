package com.animalcare.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "zoo_animals")
public class ZooAnimal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "species", nullable = false)
    private String species;

    public enum Sex {
        Male,
        Female,
        Unknown
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "sex", nullable = false)
    private Sex sex;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "weight", nullable = false)
    private float weight;

    @Column(name = "date_of_arrival", nullable = false)
    private LocalDate dateOfArrival;

    @Column(name = "date_of_departure")
    private LocalDate dateOfDeparture;

    @Column(name = "cause_of_departure")
    private String causeOfDeparture;

    @Column(name = "exhibit_id", nullable = false)
    private int exhibitId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // Constructor, getters and setters


    public ZooAnimal(int id, String species, Sex sex, LocalDate dateOfBirth, int age, float weight, LocalDate dateOfArrival, LocalDate dateOfDeparture, String causeOfDeparture, int exhibitId, LocalDateTime createdAt) {
        this.id = id;
        this.species = species;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.weight = weight;
        this.dateOfArrival = dateOfArrival;
        this.dateOfDeparture = dateOfDeparture;
        this.causeOfDeparture = causeOfDeparture;
        this.exhibitId = exhibitId;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public LocalDate getDateOfArrival() {
        return dateOfArrival;
    }

    public void setDateOfArrival(LocalDate dateOfArrival) {
        this.dateOfArrival = dateOfArrival;
    }

    public LocalDate getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(LocalDate dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    public String getCauseOfDeparture() {
        return causeOfDeparture;
    }

    public void setCauseOfDeparture(String causeOfDeparture) {
        this.causeOfDeparture = causeOfDeparture;
    }

    public int getExhibitId() {
        return exhibitId;
    }

    public void setExhibitId(int exhibitId) {
        this.exhibitId = exhibitId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
