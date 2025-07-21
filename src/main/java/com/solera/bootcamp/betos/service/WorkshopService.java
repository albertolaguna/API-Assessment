package com.solera.bootcamp.betos.service;

import com.solera.bootcamp.betos.model.Workshop;
import com.solera.bootcamp.betos.repository.WorkshopRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkshopService {

    private final WorkshopRepository workshopRepository;

    public WorkshopService(WorkshopRepository workshopRepository) {
        this.workshopRepository = workshopRepository;
    }

    public void createWorkshop(Workshop workshop) {
        workshopRepository.save(workshop);
    }

    public List<Workshop> getAllWorkshops() {
        return (List<Workshop>) workshopRepository.findAll();
    }

    public Workshop getWorkshopById(Long id) {
        return workshopRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(0));
    }

    public void deleteWorkshopById(Long id) {
        if (!workshopRepository.existsById(id)) {
            throw new EmptyResultDataAccessException(0);
        }
        workshopRepository.deleteById(id);
    }

    public Workshop updateWorkshopById(Long id, Workshop workshop) {
        if (!workshopRepository.existsById(id)) {
            throw new EmptyResultDataAccessException(0);
        }
        workshop.setId(id);
        return workshopRepository.save(workshop);
    }
}
