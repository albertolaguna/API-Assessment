package com.solera.bootcamp.betos.controller;

import com.solera.bootcamp.betos.model.Workshop;
import com.solera.bootcamp.betos.service.WorkshopService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/workshops")
public class WorkshopController {

    private final WorkshopService workshopService;

    public WorkshopController(WorkshopService workshopService) {
        this.workshopService = workshopService;
    }

    @PostMapping
    public ResponseEntity<Workshop> createWorkshop(@RequestBody Workshop workshop) {
        workshopService.createWorkshop(workshop);
        return ResponseEntity.ok(workshop);
    }

    @GetMapping
    public ResponseEntity<List<Workshop>> getAllWorkshop() {
        return ResponseEntity.ok(workshopService.getAllWorkshops());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Workshop> getWorkshopById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(workshopService.getWorkshopById(id));
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkshopById(@PathVariable Long id) {
        try {
            workshopService.deleteWorkshopById(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Workshop> updateWorkshop(@PathVariable Long id, @RequestBody Workshop workshop) {
        try {
            workshopService.updateWorkshopById(id, workshop);
            return ResponseEntity.ok(workshop);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }

    }
}
