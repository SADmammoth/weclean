package com.example.weclean.service;

import com.example.weclean.domain.VacuumCLeaner;
import com.example.weclean.repo.VacuumCleanerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VacuumCleanerService {
    @Autowired
private VacuumCleanerRepository vacuumCleanerRepository;

    public VacuumCLeaner createVacuumCleaner(String code, String model, String manufacturer,
                                             Float price, String construction, String cleaningFeatures,
                                             String dustCollectorType, Float volumeOfDustCollector, Integer powerConsumption,
                                             String powerSource, String color, Float powerCordLength, Float weight, Float noiseLevel){
    return vacuumCleanerRepository.save(new VacuumCLeaner(code, model, manufacturer,
            price, construction, cleaningFeatures,
            dustCollectorType, volumeOfDustCollector, powerConsumption,
            powerSource, color, powerCordLength, weight, noiseLevel));
    }

    public long total() {
        return vacuumCleanerRepository.count();
    }
}
