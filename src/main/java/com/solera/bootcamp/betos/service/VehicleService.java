package com.solera.bootcamp.betos.service;

import com.solera.bootcamp.betos.model.Vehicle;
import com.solera.bootcamp.betos.model.Workshop;
import com.solera.bootcamp.betos.repository.VehicleRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public void createVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Workshop body cannot be empty");
        }
        if (vehicle.getModel() == null || vehicle.getModel().trim().isEmpty()) {
            throw new IllegalArgumentException("Model is mandatory");
        }
        vehicle.setModel(vehicle.getModel().trim());
        if (vehicle.getBrand() == null || vehicle.getBrand().trim().isEmpty()) {
            throw new IllegalArgumentException("Brand is mandatory");
        }
        vehicle.setBrand(vehicle.getBrand().trim());
        if (vehicle.getModelYear() == null) {
            throw new IllegalArgumentException("Model Year is mandatory");
        }
        vehicle.setModelYear(vehicle.getModelYear());
        if (vehicle.getColor() != null) {
            vehicle.setColor(vehicle.getColor().trim());
        }
        if (vehicle.getVin() != null) {
            vehicle.setVin(vehicle.getVin().trim());
        }
        if (vehicle.getId() != null) {
            if (vehicleRepository.findById(vehicle.getId()).isPresent()) {
                throw new EntityExistsException("Vehicle ID already exists");
            }
            vehicle.setId(null);
        }
        vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return (List<Vehicle>) vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found"));
    }

    public void deleteVehicleById(Long id) {
        Vehicle vehicleToDelete = vehicleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found"));
        if (vehicleToDelete.getParts() != null && !vehicleToDelete.getParts().isEmpty()) {
            throw new IllegalStateException("The vehicle cannot be deleted due at least one part is associated to it");
        }
        vehicleRepository.deleteById(id);
    }

    public void updateVehicleById(Long id, Vehicle vehicle) {
        if (!vehicleRepository.existsById(id)) {
            throw new EntityNotFoundException("Vehicle not found");
        }
        vehicle.setId(id);
        vehicleRepository.save(vehicle);
    }
}
