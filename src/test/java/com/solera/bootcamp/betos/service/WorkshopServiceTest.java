package com.solera.bootcamp.betos.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.solera.bootcamp.betos.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WorkshopServiceTest {

    @Autowired
    private WorkshopService workshopService;

    @Test
    void createWorkshopTest() {
        // Instantiate a workshop object
        Workshop workshop = new Workshop();
        workshop.setName("workshop 1");
        workshop.setDescription("the workshop 1");
        // Create a workshop in the DB
        workshopService.createWorkshop(workshop);
        // Verify the workshop has been created correctly by its name
        assertEquals("workshop 1", workshop.getName());
    }

    @Test
    void createAnExistingWorkshop() {
        // Instantiate a workshop object
        Workshop workshop = new Workshop();
        workshop.setName("workshop 1");
        workshop.setDescription("the workshop 1");
        // Create a workshop in the DB
        workshopService.createWorkshop(workshop);

        // Instantiate a second workshop object with the same ID of the first one
        Workshop secondWorkshop = new Workshop();
        secondWorkshop.setId(workshop.getId());
        secondWorkshop.setName("workshop 1");
        secondWorkshop.setDescription("the workshop 1");

        // Verify the service throws an exception
        assertThrows(EntityExistsException.class, () -> {
            // Try to create the second workshop
            workshopService.createWorkshop(secondWorkshop);
        });
    }

    @Test
    void getWorkshopById() {
        // Instantiate a workshop object
        Workshop workshop = new Workshop();
        workshop.setName("workshop 1");
        workshop.setDescription("the workshop 1");

        // Create a workshop in the DB
        workshopService.createWorkshop(workshop);

        // Get the workshop by the ID
        Workshop workshopFound = workshopService.getWorkshopById(workshop.getId());

        // Verify the workshop found is not null and is the expected workshop by its name
        assertNotNull(workshopFound);
        assertEquals("workshop 1", workshop.getName());
    }

    @Test
    void getUnexistingWorkshopById() {
        // Verify the service throws an exception
        assertThrows(EntityNotFoundException.class, () -> {
            // Get a workshop with ID 999 (doesn't exist due the ID is very big)
            workshopService.getWorkshopById(999L);
        });
    }

    @Test
    void deleteWorkshopById() {
        // Instantiate a workshop object
        Workshop workshop = new Workshop();
        workshop.setName("workshop 1");
        workshop.setDescription("the workshop 1");
        // Create a workshop in the DB
        workshopService.createWorkshop(workshop);
        // Delete workshop
        workshopService.deleteWorkshopById(workshop.getId());
        // Verify the service throws an exception due the workshop doesn't exist anymore
        assertThrows(EntityNotFoundException.class, () -> {
            // Get a workshop with the ID of the deleted workshop
            workshopService.getWorkshopById(workshop.getId());
        });
    }

    @Test
    void deleteUnexistingWorkshopById() {
        // Verify the service throws an exception
        assertThrows(EntityNotFoundException.class, () -> {
            // Try to delete a workshop with ID 999 (doesn't exist due the ID is very big)
            workshopService.getWorkshopById(999L);
        });
    }

    @Test
    void updateWorkshopTest() {
        // Instantiate a workshop object
        Workshop workshop = new Workshop();
        workshop.setName("workshop 1");
        workshop.setDescription("the workshop 1");
        // Create a workshop in the DB
        workshopService.createWorkshop(workshop);
        // Update the workshop name
        workshop.setName("workshop 1 Updated");
        workshopService.updateWorkshopById(workshop.getId(), workshop);
        // Verify the workshop has been created correctly by its name
        assertEquals("workshop 1 Updated", workshop.getName());
    }

    @Test
    void updateUnexistingWorkshopById() {
        // Instantiate a workshop object
        Workshop workshop = new Workshop();
        workshop.setName("workshop 1");
        workshop.setDescription("the workshop 1");
        // Create a workshop in the DB
        workshopService.createWorkshop(workshop);
        // Verify the service throws an exception
        assertThrows(EntityNotFoundException.class, () -> {
            workshop.setName("workshop 1 Updated");
            // Try to delete a workshop with ID 999 (doesn't exist due the ID is very big)
            workshopService.updateWorkshopById(999L, workshop);
        });
    }
}

