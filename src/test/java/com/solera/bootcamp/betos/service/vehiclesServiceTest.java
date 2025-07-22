/*
* "model": "Vehicle Model 1",
  "brand": "Vehicle Brand 1",
  "modelYear": "2020",
  "color": "Black",
  "vin": "123456789",
  "workshop": {
    "id": 1

*
*
* */
/*
*  creation
*   creation with an existing
*   get vehicle by id
*   get an unexisting vehicle
*   get
*   delete
* */

package com.solera.bootcamp.betos.service;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.solera.bootcamp.betos.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class vehiclesServiceTest {


        @Autowired
        private VehicleService vehicleService;

        @Test
        void createVehicleTest() {
            // Instantiate a workshop object
            Vehicle vehicle = new Vehicle();
            vehicle.setId(1L);
            vehicle.setBrand("vw");
            vehicle.setModel("Polo");
            vehicle.setColor("negro");
            vehicle.setVin("21sdf54f5wf51f");
            // Create a workshop in the DB
            vehicleService.createVehicle(vehicle);
            // Verify the workshop has been created correctly by its name
            assertEquals("21sdf54f5wf51f",vehicle.getVin());

        }
    @Test
    void createAnExistingVehicle(){
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1L);
        vehicle.setBrand("vw");
        vehicle.setModel("Polo");
        vehicle.setColor("negro");
        vehicle.setVin("21sdf54f5wf51f");
        // Create a workshop in the DB
        vehicleService.createVehicle(vehicle);

        // Instantiate a second workshop object with the same ID of the first one
        Vehicle vehicle2 = new Vehicle();
        vehicle2.setId(vehicle.getId());
        vehicle2.setBrand("Chevrolet");
        vehicle2.setModel("Aveo");
        vehicle2.setColor("rojo");
        vehicle2.setVin("54wfg8t5yj45df1s6");

        // Verify the service throws an exception
        assertThrows(EntityExistsException.class, () ->
        {
            // Create a workshop in the DB
            vehicleService.createVehicle(vehicle2);
        });
    }
    @Test
    void getVehicleById() {
        // Instantiate a vehicle object
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1L);
        vehicle.setBrand("vw");
        vehicle.setModel("Polo");
        vehicle.setColor("negro");
        vehicle.setVin("21sdf54f5wf51f");
        // Create a vehicle in the DB
        vehicleService.createVehicle(vehicle);

        // Get the vehicle by the ID
        Vehicle vehicle1= vehicleService.getVehicleById(vehicle.getId());

        // Verify the vehicle found is not null and is the expected workshop by its name
        assertNotNull(vehicle);
        assertEquals("21sdf54f5wf51f", vehicle.getVin());
    }
    @Test
    void getUnexistingVehicleById() {
        // Verify the service throws an exception
        assertThrows(EntityNotFoundException.class, () -> {
            // Get a workshop with ID 999 (doesn't exist due the ID is very big)
            vehicleService.getVehicleById(1L);
        });
    }
    @Test
    void deleteVehicleById(){
        // Instantiate a workshop object
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("vw");
        vehicle.setModel("Polo");
        vehicle.setColor("negro");
        vehicle.setVin("21sdf54f5wf51f2z3");
        // Create a vehicle in the DB
        vehicleService.createVehicle(vehicle);
        // Delete workshop
        vehicleService.deleteVehicleById(vehicle.getId());

        // Verify the service throws an exception due the workshop doesn't exist anymore
        assertThrows(EntityNotFoundException.class, () -> {
            // Get a workshop with the ID of the deleted workshop
            vehicleService.getVehicleById(vehicle.getId());
        });
    }
    }