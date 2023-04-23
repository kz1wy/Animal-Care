package com.animalcare.service;

import com.animalcare.model.FeedingSchedule;
import com.animalcare.model.ZooAnimal;
import com.animalcare.repository.FeedingScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FeedingScheduleService {

    private FeedingScheduleRepository feedingScheduleRepository;

    public FeedingScheduleService(FeedingScheduleRepository feedingScheduleRepository) {
        this.feedingScheduleRepository = feedingScheduleRepository;
    }

    public List<FeedingSchedule> findAllFeedingSchedules() {
        return feedingScheduleRepository.findAll();
    }

    public Optional<FeedingSchedule> findFeedingScheduleById(int id) {
        return feedingScheduleRepository.findById(id);
    }

    public List<FeedingSchedule> findFeedingSchedulesByAnimal(ZooAnimal animal) {
        return feedingScheduleRepository.findByAnimal(animal);
    }

    public List<FeedingSchedule> findFeedingSchedulesByEnclosureId(int enclosureId) {
        return feedingScheduleRepository.findByEnclosureId(enclosureId);
    }

    public FeedingSchedule saveFeedingSchedule(FeedingSchedule feedingSchedule) {
        feedingSchedule.setCreatedAt(LocalDateTime.now());
        return feedingScheduleRepository.save(feedingSchedule);
    }

    public FeedingSchedule updateFeedingSchedule(FeedingSchedule feedingSchedule) {
        Optional<FeedingSchedule> optionalFeedingSchedule = feedingScheduleRepository.findById(feedingSchedule.getId());
        if (optionalFeedingSchedule.isPresent()) {
            FeedingSchedule existingFeedingSchedule = optionalFeedingSchedule.get();
            existingFeedingSchedule.setAnimal(feedingSchedule.getAnimal());
            existingFeedingSchedule.setEnclosure(feedingSchedule.getEnclosure());
            existingFeedingSchedule.setFeedingTime(feedingSchedule.getFeedingTime());
            existingFeedingSchedule.setFeedingNotes(feedingSchedule.getFeedingNotes());
            return feedingScheduleRepository.save(existingFeedingSchedule);
        } else {
            throw new RuntimeException("Feeding schedule not found with id: " + feedingSchedule.getId());
        }
    }

    public void deleteFeedingScheduleById(int id) {
        feedingScheduleRepository.deleteById(id);
    }
}
