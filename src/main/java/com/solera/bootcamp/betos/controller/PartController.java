package com.solera.bootcamp.betos.controller;

import com.solera.bootcamp.betos.model.Part;
import com.solera.bootcamp.betos.service.PartService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parts")
public class PartController {

    private final PartService partService;

    public PartController(PartService partService) {
        this.partService = partService;
    }

    @PostMapping
    public ResponseEntity<Part> createPart(@RequestBody Part part) {
        partService.createPart(part);
        return ResponseEntity.ok(part);
    }

    @GetMapping
    public ResponseEntity<List<Part>> GetAllPart() {
        return ResponseEntity.ok(partService.getAllParts());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Part> getPartById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(partService.getPartById(id));
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePartById(@PathVariable Long id) {
        try {
            partService.deletePartById(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Part> updatePart(@PathVariable Long id, @RequestBody Part part) {
        try {
            partService.updatePartById(id, part);
            return ResponseEntity.ok(part);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }

    }
}
