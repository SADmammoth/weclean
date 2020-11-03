package com.example.weclean.service;

import com.example.weclean.domain.VacuumCleaner;
import com.example.weclean.repo.VacuumCleanerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class VacuumCleanerService {
    @Autowired
    private VacuumCleanerRepository vacuumCleanerRepository;
    @Transactional
    public VacuumCleaner createVacuumCleaner(String model, String manufacturer,
                                             Float price/*, String construction, String cleaningFeatures,
                                             String dustCollectorType, Float volumeOfDustCollector, Integer powerConsumption,
                                             String powerSource, String color, Float powerCordLength, Float weight, Float noiseLevel*/){
        return vacuumCleanerRepository.save(new VacuumCleaner(model, manufacturer,
                price/*, construction, cleaningFeatures,
            dustCollectorType, volumeOfDustCollector, powerConsumption,
            powerSource, color, powerCordLength, weight, noiseLevel*/));
    }

    @Transactional(readOnly = true)
    public List<VacuumCleaner> getAllVacuumCleaners(final int count) {
        return StreamSupport.stream(this.vacuumCleanerRepository.findAll().spliterator(), false).limit(count).collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public Optional<VacuumCleaner> getVacuumCleaner(final int id) {
        return this.vacuumCleanerRepository.findById(id);
    }

    public long total() {
        return vacuumCleanerRepository.count();
    }
}
