package com.solera.bootcamp.betos.service;

import com.solera.bootcamp.betos.model.Part;
import com.solera.bootcamp.betos.model.Vehicle;
import com.solera.bootcamp.betos.model.Workshop;
import com.solera.bootcamp.betos.repository.PartRepository;
import com.solera.bootcamp.betos.repository.VehicleRepository;
import com.solera.bootcamp.betos.repository.WorkshopRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final PartRepository partRepository;

    public VehicleService(VehicleRepository vehicleRepository, PartRepository partRepository) {
        this.vehicleRepository = vehicleRepository;
        this.partRepository = partRepository;
    }

    public void createVehicle(Vehicle vehicle) {
        if (vehicle.getParts() != null) {
            List<Part> managedParts = vehicle.getParts().stream()
                    .map(part -> partRepository.findById(part.getId())
                            .orElseThrow(() -> new EntityNotFoundException("Part not found: " + part.getId())))
                    .toList();
            vehicle.setParts(managedParts);
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
        if (!vehicleRepository.existsById(id)) {
            throw new EntityNotFoundException("Vehicle not found");
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
