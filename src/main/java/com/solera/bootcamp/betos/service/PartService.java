package com.solera.bootcamp.betos.service;

import com.solera.bootcamp.betos.model.Part;
import com.solera.bootcamp.betos.repository.PartRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartService {

    private final PartRepository partRepository;

    public PartService(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    public void createPart(Part part) {
        partRepository.save(part);
    }

    public List<Part> getAllParts() {
        return (List<Part>) partRepository.findAll();
    }

    public Part getPartById(Long id) {
        return partRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(0));
    }

    public void deletePartById(Long id) {
        if (!partRepository.existsById(id)) {
            throw new EmptyResultDataAccessException(0);
        }
        partRepository.deleteById(id);
    }

    public Part updatePartById(Long id, Part part) {
        if (!partRepository.existsById(id)) {
            throw new EmptyResultDataAccessException(0);
        }
        part.setId(id);
        return partRepository.save(part);
    }
}
