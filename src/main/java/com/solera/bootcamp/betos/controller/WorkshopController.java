package com.solera.bootcamp.betos.controller;

import com.solera.bootcamp.betos.dto.CustomResponse;
import com.solera.bootcamp.betos.model.Workshop;
import com.solera.bootcamp.betos.service.WorkshopService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
    public ResponseEntity<?> createWorkshop(@RequestBody Workshop workshop) {
        try {
            workshopService.createWorkshop(workshop);
            return ResponseEntity.status(HttpStatus.CREATED).body(workshop);
        } catch (EntityExistsException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<Workshop>> getAllWorkshop() {
        return ResponseEntity.ok(workshopService.getAllWorkshops());

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWorkshopById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(workshopService.getWorkshopById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWorkshopById(@PathVariable Long id) {
        try {
            workshopService.deleteWorkshopById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(HttpStatus.OK.value(), "Workshop has been deleted successfully"));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new CustomResponse(HttpStatus.CONFLICT.value(), e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateWorkshop(@PathVariable Long id, @RequestBody Workshop workshop) {
        try {
            workshopService.updateWorkshopById(id, workshop);
            return ResponseEntity.ok(workshop);
        } catch (EntityNotFoundException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()));
        }

    }
}
