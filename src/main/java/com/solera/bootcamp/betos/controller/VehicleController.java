package com.solera.bootcamp.betos.controller;

import com.solera.bootcamp.betos.dto.CustomResponse;
import com.solera.bootcamp.betos.model.Vehicle;
import com.solera.bootcamp.betos.service.VehicleService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<?> createVehicle(@RequestBody Vehicle vehicle) {
        try {
            vehicleService.createVehicle(vehicle);
            return ResponseEntity.status(HttpStatus.CREATED).body(vehicle);
        } catch (EntityExistsException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVehicleById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(vehicleService.getVehicleById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVehicleById(@PathVariable Long id) {
        try {
            vehicleService.deleteVehicleById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(HttpStatus.OK.value(), "Vehicle has been deleted successfully"));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()));
        } catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new CustomResponse(HttpStatus.CONFLICT.value(), e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        try {
            vehicleService.updateVehicleById(id, vehicle);
            return ResponseEntity.ok(vehicle);
        } catch (EntityNotFoundException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()));
        }
    }
}
