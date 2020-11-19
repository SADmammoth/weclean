package com.example.weclean.service;

import com.example.weclean.domain.enums.CleaningFeatures;
import com.example.weclean.domain.enums.Units;
import com.example.weclean.domain.VacuumCleaner;
import com.example.weclean.repo.VacuumCleanerRepository;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@GraphQLApi
public class VacuumCleanerService {
    @Autowired
    private VacuumCleanerRepository vacuumCleanerRepository;
    @Transactional
    @GraphQLMutation
    public VacuumCleaner createVacuumCleaner(@GraphQLNonNull String model, @GraphQLNonNull String manufacturer,
                                             @GraphQLNonNull Double price, @GraphQLNonNull String construction, @GraphQLNonNull Set<CleaningFeatures> cleaningFeatures,
                                             @GraphQLNonNull String dustCollectorType, @GraphQLNonNull Double volumeOfDustCollector, @GraphQLNonNull Double powerConsumption,
                                             @GraphQLNonNull String powerSource, @GraphQLNonNull  String color, @GraphQLNonNull Double powerCordLength, @GraphQLNonNull Double weight, @GraphQLNonNull Double noiseLevel, @GraphQLNonNull Double discount){
        return vacuumCleanerRepository.save(new VacuumCleaner(model, manufacturer, price, construction, cleaningFeatures, dustCollectorType, volumeOfDustCollector, powerConsumption, powerSource, color, powerCordLength, weight, noiseLevel, discount));
    }

    @Transactional(readOnly = true)
    @GraphQLQuery
    public List<VacuumCleaner> getVacuumCleaners(final int count) {
        return StreamSupport.stream(this.vacuumCleanerRepository.findAll().spliterator(), false).limit(count).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @GraphQLQuery
    public List<VacuumCleaner> getAllVacuumCleaners() {
        return StreamSupport.stream(this.vacuumCleanerRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @GraphQLQuery
    public Optional<VacuumCleaner> getVacuumCleaner( @GraphQLNonNull final int id) {
        return this.vacuumCleanerRepository.findById(id);
    }

    @GraphQLQuery
    public long total() {
        return vacuumCleanerRepository.count();
    }

    @GraphQLQuery
    public String getUnit(@GraphQLNonNull final String fieldName){
        return Units.valueOf(fieldName).value;
    }

    @GraphQLQuery
    public Map<Object, Object> getUnits(){
        return Arrays.stream(Units.values()).collect(Collectors.toMap(unit->unit, unit->unit.value));
    }
}
