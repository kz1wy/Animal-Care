package com.animalcare.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "breeding_pairs")
public class BreedingPair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "breeding_program_id")
    private BreedingProgram breedingProgram;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "male_animal_id")
    private ZooAnimal maleAnimal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "female_animal_id")
    private ZooAnimal femaleAnimal;

    @Column(name = "breeding_notes", length = 1000)
    private String breedingNotes;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // Constructor, getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BreedingProgram getBreedingProgram() {
        return breedingProgram;
    }

    public void setBreedingProgram(BreedingProgram breedingProgram) {
        this.breedingProgram = breedingProgram;
    }

    public ZooAnimal getMaleAnimal() {
        return maleAnimal;
    }

    public void setMaleAnimal(ZooAnimal maleAnimal) {
        this.maleAnimal = maleAnimal;
    }

    public ZooAnimal getFemaleAnimal() {
        return femaleAnimal;
    }

    public void setFemaleAnimal(ZooAnimal femaleAnimal) {
        this.femaleAnimal = femaleAnimal;
    }

    public String getBreedingNotes() {
        return breedingNotes;
    }

    public void setBreedingNotes(String breedingNotes) {
        this.breedingNotes = breedingNotes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
