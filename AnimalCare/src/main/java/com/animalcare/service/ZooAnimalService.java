package com.animalcare.service;

import com.animalcare.model.ZooAnimal;
import com.animalcare.repository.ZooAnimalRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ZooAnimalService {

    private final ZooAnimalRepository zooAnimalRepository;

    public ZooAnimalService(ZooAnimalRepository zooAnimalRepository) {
        this.zooAnimalRepository = zooAnimalRepository;
    }

    public ZooAnimal create(ZooAnimal zooAnimal) {
        zooAnimal.setCreatedAt(LocalDateTime.now());
        return zooAnimalRepository.save(zooAnimal);
    }

    public List<ZooAnimal> findAll() {
        return zooAnimalRepository.findAll();
    }

    public ZooAnimal findById(int id) {
        Optional<ZooAnimal> zooAnimal = zooAnimalRepository.findById(id);
        if (zooAnimal.isPresent()) {
            return zooAnimal.get();
        } else {
            throw new EntityNotFoundException("ZooAnimal not found with ID " + id);
        }
    }

    public ZooAnimal update(int id, ZooAnimal zooAnimal) {
        ZooAnimal existingZooAnimal = findById(id);
        existingZooAnimal.setSpecies(zooAnimal.getSpecies());
        existingZooAnimal.setSex(zooAnimal.getSex());
        existingZooAnimal.setDateOfBirth(zooAnimal.getDateOfBirth());
        existingZooAnimal.setAge(zooAnimal.getAge());
        existingZooAnimal.setWeight(zooAnimal.getWeight());
        existingZooAnimal.setDateOfArrival(zooAnimal.getDateOfArrival());
        existingZooAnimal.setDateOfDeparture(zooAnimal.getDateOfDeparture());
        existingZooAnimal.setCauseOfDeparture(zooAnimal.getCauseOfDeparture());
        existingZooAnimal.setExhibitId(zooAnimal.getExhibitId());
        return zooAnimalRepository.save(existingZooAnimal);
    }

    public void delete(int id) {
        zooAnimalRepository.deleteById(id);
    }

}
