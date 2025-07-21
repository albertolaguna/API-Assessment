package com.solera.bootcamp.betos.repository;

import com.solera.bootcamp.betos.model.Part;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends CrudRepository<Part, Long> {
}
