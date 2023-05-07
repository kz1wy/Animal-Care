package com.animalcare.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.animalcare.model.ZooAnimal;
import com.animalcare.repository.ZooAnimalRepository;
import com.animalcare.service.ZooAnimalService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ZooAnimalController.class)
public class ZooAnimalControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private ZooAnimalController zooAnimalController;

    @Mock
    private ZooAnimalService zooAnimalService;

    @Mock
    private ZooAnimalRepository zooAnimalRepository;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        zooAnimalController = new ZooAnimalController(zooAnimalService);
    }

    @Test
    public void testAddZooAnimal() throws Exception {
        // create a ZooAnimal object
        ZooAnimal zooAnimal = new ZooAnimal();
        zooAnimal.setSpecies("Lion");

        // perform the POST request
        mockMvc.perform(post("/zoo-animals/add")
                        .flashAttr("zooAnimal", zooAnimal))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/zoo-animals"));

        // verify that the service method was called with the correct argument
        verify(zooAnimalService).create(zooAnimal);
    }
    @Test
    public void testDeleteZooAnimal() {
        // Arrange
        ZooAnimal zooAnimal = new ZooAnimal();
        zooAnimal.setId(1000);
        zooAnimal.setSpecies("Tiger");
        zooAnimal.setSex(ZooAnimal.Sex.Male);
        zooAnimal.setDateOfBirth(LocalDate.of(2015, 1, 1));
        zooAnimal.setAge(6);
        zooAnimal.setWeight(200);
        zooAnimal.setDateOfArrival(LocalDate.of(2021, 1, 1));
        zooAnimal.setExhibitId(1);
        zooAnimal.setCreatedAt(LocalDateTime.now());

        // Act
        zooAnimalService.delete(1);

        // Assert
        verify(zooAnimalService, times(1)).findById(1000);
        verify(zooAnimalRepository, times(1)).delete(zooAnimal);
        assertEquals(null, zooAnimal.getDateOfDeparture());
        assertEquals(null, zooAnimal.getCauseOfDeparture());
    }
}