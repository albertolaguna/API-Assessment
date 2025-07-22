package com.solera.bootcamp.betos.controller;

import com.solera.bootcamp.betos.dto.CustomResponse;
import com.solera.bootcamp.betos.model.Part;
import com.solera.bootcamp.betos.service.PartService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
    public ResponseEntity<?> createPart(@RequestBody Part part) {
        try {
            partService.createPart(part);
            return ResponseEntity.status(HttpStatus.CREATED).body(part);
        } catch (EntityExistsException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<Part>> GetAllPart() {
        return ResponseEntity.ok(partService.getAllParts());

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPartById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(partService.getPartById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePartById(@PathVariable Long id) {
        try {
            partService.deletePartById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(HttpStatus.OK.value(), "Part has been deleted successfully"));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()));
        } catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new CustomResponse(HttpStatus.CONFLICT.value(), e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePart(@PathVariable Long id, @RequestBody Part part) {
        try {
            partService.updatePartById(id, part);
            return ResponseEntity.ok(part);
        } catch (EntityNotFoundException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()));
        }

    }
}
