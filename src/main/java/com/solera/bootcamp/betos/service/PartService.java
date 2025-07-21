package com.solera.bootcamp.betos.service;

import com.solera.bootcamp.betos.model.Part;
import com.solera.bootcamp.betos.repository.PartRepository;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

public class PartService {

    private final PartRepository partRepository;

    public PartService(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    public void createWorkshop(Part part) {
        partRepository.save(part);
    }

    public List<Part> getAllWorkshops() {
        return (List<Part>) partRepository.findAll();
    }

    public Part getWorkshopById(Long id) {
        return partRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(0));
    }

    public void deleteWorkshopById(Long id) {
        if (!partRepository.existsById(id)) {
            throw new EmptyResultDataAccessException(0);
        }
        partRepository.deleteById(id);
    }

    public Part updateWorkshopById(Long id, Part part) {
        if (!partRepository.existsById(id)) {
            throw new EmptyResultDataAccessException(0);
        }
        part.setId(id);
        return partRepository.save(part);
    }
}
