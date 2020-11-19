package com.example.weclean.repo;

import com.example.weclean.domain.VacuumCleaner;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VacuumCleanerRepository extends CrudRepository<VacuumCleaner, Integer> {
List<VacuumCleaner> findAllByManufacturerIgnoreCaseContainingOrModelIgnoreCaseContaining(@NonNull String manufacturer, @NonNull String model);}
