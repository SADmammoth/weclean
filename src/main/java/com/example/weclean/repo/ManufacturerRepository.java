package com.example.weclean.repo;

import com.example.weclean.domain.Manufacturer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ManufacturerRepository extends CrudRepository<Manufacturer, Integer> {
    boolean existsByName(String name);
    Optional<Manufacturer> findByName(String name);
    Optional<Manufacturer> findAllByNameContainsIgnoreCase(String name);
}
