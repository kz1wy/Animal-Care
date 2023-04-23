package com.animalcare.repository;

import com.animalcare.model.FeedingSchedule;
import com.animalcare.model.ZooAnimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedingScheduleRepository extends JpaRepository<FeedingSchedule, Integer> {
    List<FeedingSchedule> findByAnimal(ZooAnimal animal);

    List<FeedingSchedule> findByEnclosureId(int enclosureId);
}