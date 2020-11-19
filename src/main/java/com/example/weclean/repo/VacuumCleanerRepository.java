package com.example.weclean.repo;

import com.example.weclean.domain.Manufacturer;
import com.example.weclean.domain.VacuumCleaner;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VacuumCleanerRepository extends CrudRepository<VacuumCleaner, Integer> {
List<VacuumCleaner> findAllByModelIgnoreCaseContaining(@NonNull String model);
List<VacuumCleaner> findAllByManufacturer(@NonNull Manufacturer manufacturer);
}
