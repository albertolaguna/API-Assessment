package com.solera.bootcamp.betos.repository;

import com.solera.bootcamp.betos.model.Workshop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkshopRepository extends CrudRepository<Workshop, Long> {
}
