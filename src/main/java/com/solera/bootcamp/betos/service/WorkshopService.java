package com.solera.bootcamp.betos.service;

import com.solera.bootcamp.betos.model.Vehicle;
import com.solera.bootcamp.betos.model.Workshop;
import com.solera.bootcamp.betos.repository.WorkshopRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkshopService {

    private final WorkshopRepository workshopRepository;

    public WorkshopService(WorkshopRepository workshopRepository) {
        this.workshopRepository = workshopRepository;
    }

    public void createWorkshop(Workshop workshop) {
        if (workshop == null) {
            throw new IllegalArgumentException("Workshop body cannot be empty");
        }
        if (workshop.getName() == null || workshop.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name is mandatory");
        }
        workshop.setName(workshop.getName().trim());
        if (workshop.getDescription() != null) {
            workshop.setDescription(workshop.getDescription().trim());
        }
        if (workshop.getId() != null) {
            if (workshopRepository.findById(workshop.getId()).isPresent()) {
                throw new EntityExistsException("Workshop ID already exists");
            }
            workshop.setId(null);
        }
        workshopRepository.save(workshop);
    }

    public List<Workshop> getAllWorkshops() {
        return (List<Workshop>) workshopRepository.findAll();
    }

    public Workshop getWorkshopById(Long id) {
        return workshopRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Workshop not found"));
    }

    public void deleteWorkshopById(Long id) {
        Workshop workshopToDelete = workshopRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Workshop not found"));
        if (workshopToDelete.getVehicles() != null && !workshopToDelete.getVehicles().isEmpty()) {
            throw new IllegalStateException("The workshop cannot be deleted due at least one vehicle is associated to it");
        }
        workshopRepository.deleteById(id);
    }

    public void updateWorkshopById(Long id, Workshop workshop) {
        if (workshop == null) {
            throw new IllegalArgumentException("Workshop body cannot be empty");
        }
        if (workshop.getName() == null || workshop.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name is mandatory");
        }
        workshop.setName(workshop.getName().trim());
        if (workshop.getDescription() != null) {
            workshop.setDescription(workshop.getDescription().trim());
        }
        if (!workshopRepository.existsById(id)) {
            throw new EntityNotFoundException("Workshop not found");
        }
        workshop.setId(id);
        workshopRepository.save(workshop);
    }
}
