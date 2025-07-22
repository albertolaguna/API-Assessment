package com.solera.bootcamp.betos.service;

import com.solera.bootcamp.betos.model.Part;
import com.solera.bootcamp.betos.model.Vehicle;
import com.solera.bootcamp.betos.repository.PartRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartService {

    private final PartRepository partRepository;

    public PartService(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    public void createPart(Part part) {
        if (part == null) {
            throw new IllegalArgumentException("Part body cannot be empty");
        }
        if (part.getName() == null || part.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name is mandatory");
        }
        part.setName(part.getName().trim());
        if (part.getDescription() != null) {
            part.setDescription(part.getDescription().trim());
        }
        if (part.getId() != null) {
            if (partRepository.findById(part.getId()).isPresent()) {
                throw new EntityExistsException("Part ID already exists");
            }
            part.setId(null);
        }
        partRepository.save(part);
    }

    public List<Part> getAllParts() {
        return (List<Part>) partRepository.findAll();
    }

    public Part getPartById(Long id) {
        return partRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Part not found"));
    }

    public void deletePartById(Long id) {
        Part partToDelete = partRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Part not found"));
        if (partToDelete.getVehicles() != null && !partToDelete.getVehicles().isEmpty()) {
            throw new IllegalStateException("The part cannot be deleted due at least one vehicle is associated to it");
        }
        partRepository.deleteById(id);
    }

    public void updatePartById(Long id, Part part) {
        if (part == null) {
            throw new IllegalArgumentException("Part body cannot be empty");
        }
        if (part.getName() == null || part.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name is mandatory");
        }
        part.setName(part.getName().trim());
        if (part.getDescription() != null) {
            part.setDescription(part.getDescription().trim());
        }
        if (!partRepository.existsById(id)) {
            throw new EntityNotFoundException("Part not found");
        }
        part.setId(id);
        partRepository.save(part);
    }
}
