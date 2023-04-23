package com.animalcare.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "enclosures")
public class Enclosure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "temperature", nullable = false)
    private BigDecimal temperature;

    @Column(name = "humidity", nullable = false)
    private BigDecimal humidity;

    @Column(name = "cleanliness", nullable = false)
    private BigDecimal cleanliness;

    // Constructor, getters and setters

    public int getId() {
        return id;
    }
}
