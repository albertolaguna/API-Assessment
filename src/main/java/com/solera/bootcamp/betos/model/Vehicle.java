package com.solera.bootcamp.betos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    private String brand;
    private Integer modelYear;
    private String color;
    private String vin;
    @ManyToOne
    @JoinColumn(name = "workshop_id", nullable = true)
    @JsonIgnore
    private Workshop workshop;

    @ManyToMany(targetEntity = Part.class, mappedBy = "vehicles")
    private List<Part> parts;
}
