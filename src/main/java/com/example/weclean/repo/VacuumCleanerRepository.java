package com.example.weclean.repo;

import com.example.weclean.domain.Manufacturer;
import com.example.weclean.domain.VacuumCleaner;
import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface VacuumCleanerRepository extends CrudRepository<VacuumCleaner, Integer> {
    List<VacuumCleaner> findAllByModelIgnoreCaseContaining(@NonNull String model);
    List<VacuumCleaner> findAllByManufacturer(@NonNull Manufacturer manufacturer);

    @Query("SELECT MIN(price) FROM VacuumCleaner")
    Double getMinPrice();
    @Query("SELECT MAX(price) FROM VacuumCleaner")
    Double getMaxPrice();

    @Query("SELECT MIN(price) FROM VacuumCleaner")
    Double getMinDiscount();
    @Query("SELECT MAX(price) FROM VacuumCleaner")
    Double getMaxDiscount();

    @Query("SELECT MIN(noiseLevel) FROM VacuumCleaner")
    Double getMinNoiseLevel();
    @Query("SELECT MAX(noiseLevel) FROM VacuumCleaner")
    Double getMaxNoiseLevel();

    @Query("SELECT MIN(powerConsumption) FROM VacuumCleaner")
    Double getMinPowerConsumption();
    @Query("SELECT MAX(powerConsumption) FROM VacuumCleaner")
    Double getMaxPowerConsumption();

    @Query("SELECT MIN(powerCordLength) FROM VacuumCleaner")
    Double getMinPowerCordLength();
    @Query("SELECT MAX(powerCordLength) FROM VacuumCleaner")
    Double getMaxPowerCordLength();

    @Query("SELECT MIN(volumeOfDustCollector) FROM VacuumCleaner")
    Double getMinVolumeOfDustCollector();
    @Query("SELECT MAX(volumeOfDustCollector) FROM VacuumCleaner")
    Double getMaxVolumeOfDustCollector();

    @Query("SELECT MIN(weight) FROM VacuumCleaner")
    Double getMinWeight();
    @Query("SELECT MAX(weight) FROM VacuumCleaner")
    Double getMaxWeight();
}
