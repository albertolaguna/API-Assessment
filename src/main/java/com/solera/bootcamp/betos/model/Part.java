package com.solera.bootcamp.betos.model;

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
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToMany
    @JoinTable(name = "vehicle",
            joinColumns = @JoinColumn(name = "vehicleId"),
            inverseJoinColumns = @JoinColumn(name = "partId"))
    private List<Vehicle> vehicles;
}
