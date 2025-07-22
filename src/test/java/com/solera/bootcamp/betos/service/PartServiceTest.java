package com.solera.bootcamp.betos.service;

import com.solera.bootcamp.betos.model.Part;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Transactional
@SpringBootTest
public class PartServiceTest {

    @Autowired
    private PartService partService;

    @Test
    void createPartTest() {
        // Instantiate a part object
        Part part = new Part();
        part.setName("part 1");
        part.setDescription("the part 1");
        // Create a part in the DB
        partService.createPart(part);
        // Verify the part has been created correctly by its name
        assertEquals("part 1", part.getName());
    }

    @Test
    void createAnExistingPart() {
        // Instantiate a part object
        Part part = new Part();
        part.setName("part 1");
        part.setDescription("the part 1");
        // Create a part in the DB
        partService.createPart(part);

        // Instantiate a second part object with the same ID of the first one
        Part secondPart = new Part();
        secondPart.setId(part.getId());
        secondPart.setName("part 1");
        secondPart.setDescription("the part 1");

        // Verify the service throws an exception
        assertThrows(EntityExistsException.class, () -> {
            // Try to create the second part
            partService.createPart(secondPart);
        });
    }

    @Test
    void getPartById() {
        // Instantiate a part object
        Part part = new Part();
        part.setName("part 1");
        part.setDescription("the part 1");

        // Create a part in the DB
        partService.createPart(part);

        // Get the part by the ID
        Part partFound = partService.getPartById(part.getId());

        // Verify the part found is not null and is the expected part by its name
        assertNotNull(partFound);
        assertEquals("part 1", part.getName());
    }

    @Test
    void getUnexistingPartById() {
        // Verify the service throws an exception
        assertThrows(EntityNotFoundException.class, () -> {
            // Get a part with ID 999 (doesn't exist due the ID is very big)
            partService.getPartById(999L);
        });
    }

    @Test
    void deletePartById() {
        // Instantiate a part object
        Part part = new Part();
        part.setName("part 1");
        part.setDescription("the part 1");
        // Create a part in the DB
        partService.createPart(part);
        // Delete part
        partService.deletePartById(part.getId());
        // Verify the service throws an exception due the part doesn't exist anymore
        assertThrows(EntityNotFoundException.class, () -> {
            // Get a part with the ID of the deleted part
            partService.getPartById(part.getId());
        });
    }

    @Test
    void deleteUnexistingPartById() {
        // Verify the service throws an exception
        assertThrows(EntityNotFoundException.class, () -> {
            // Try to delete a part with ID 999 (doesn't exist due the ID is very big)
            partService.getPartById(999L);
        });
    }

    @Test
    void updatePartTest() {
        // Instantiate a part object
        Part part = new Part();
        part.setName("part 1");
        part.setDescription("the part 1");
        // Create a part in the DB
        partService.createPart(part);
        // Update the part name
        part.setName("part 1 Updated");
        partService.updatePartById(part.getId(), part);
        // Verify the part has been created correctly by its name
        assertEquals("part 1 Updated", part.getName());
    }

    @Test
    void updateUnexistingPartById() {
        // Instantiate a part object
        Part part = new Part();
        part.setName("part 1");
        part.setDescription("the part 1");
        // Create a part in the DB
        partService.createPart(part);
        // Verify the service throws an exception
        assertThrows(EntityNotFoundException.class, () -> {
            part.setName("part 1 Updated");
            // Try to delete a part with ID 999 (doesn't exist due the ID is very big)
            partService.updatePartById(999L, part);
        });
    }
}
