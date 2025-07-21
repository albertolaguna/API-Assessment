package com.solera.bootcamp.betos.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Workshop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    // @ManyToMany @Fetch(FetchMode.JOIN)
    // @OneToMany(fetch = FetchType.EAGER, mappedBy = "workshop", cascade = CascadeType.ALL)
    @OneToMany(mappedBy = "workshop")
    private List<Vehicle> vehicles;
}
