package com.example.weclean.service;

import com.example.weclean.domain.Manufacturer;
import com.example.weclean.domain.enums.*;
import com.example.weclean.domain.VacuumCleaner;
import com.example.weclean.repo.VacuumCleanerRepository;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
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
    public VacuumCleaner createVacuumCleaner(@GraphQLNonNull String model, @GraphQLNonNull Manufacturer manufacturer,
                                             @GraphQLNonNull Double price, @GraphQLNonNull Construction construction, @GraphQLNonNull Set<CleaningFeature> cleaningFeatures,
                                             @GraphQLNonNull DustCollectorType dustCollectorType, @GraphQLNonNull Double volumeOfDustCollector, @GraphQLNonNull Double powerConsumption,
                                             @GraphQLNonNull PowerSource powerSource, @GraphQLNonNull  String color, @GraphQLNonNull Double powerCordLength, @GraphQLNonNull Double weight, @GraphQLNonNull Double noiseLevel, @GraphQLNonNull Double discount){
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

    @GraphQLQuery
    public Set<String> getConstructions(){
        return Arrays.stream(Construction.values()).map(e -> e.getName()).collect(Collectors.toSet());
    }

    @GraphQLQuery
    public Set<String> getDustCollectorTypes(){
        return Arrays.stream(DustCollectorType.values()).map(e -> e.getName()).collect(Collectors.toSet());
    }

    @GraphQLQuery
    public Set<String> getCleaningFeatures(){
        return Arrays.stream(CleaningFeature.values()).map(e -> e.getName()).collect(Collectors.toSet());
    }

    @GraphQLQuery
    public Set<String> getPowerSources(){
        return Arrays.stream(PowerSource.values()).map(e -> e.getName()).collect(Collectors.toSet());
    }


    @GraphQLQuery
    public Double getMinPrice(){
        return vacuumCleanerRepository.getMinPrice();
    }
    @GraphQLQuery
    public Double getMaxPrice(){
        return vacuumCleanerRepository.getMaxPrice();
    }

    @GraphQLQuery
    public Double getMinDiscount(){
        return vacuumCleanerRepository.getMinDiscount();
    }
    @GraphQLQuery
    public Double getMaxDiscount(){
        return vacuumCleanerRepository.getMaxDiscount();
    }

    @GraphQLQuery
    public Double getMinNoiseLevel(){
        return vacuumCleanerRepository.getMinNoiseLevel();
    }
    @GraphQLQuery
    public Double getMaxNoiseLevel(){
        return vacuumCleanerRepository.getMaxNoiseLevel();
    }

    @GraphQLQuery
    public Double getMinPowerConsumption(){
        return vacuumCleanerRepository.getMinPowerConsumption();
    }
    @GraphQLQuery
    public Double getMaxPowerConsumption(){
        return vacuumCleanerRepository.getMaxPowerConsumption();
    }

    @GraphQLQuery
    public Double getMinPowerCordLength(){
        return vacuumCleanerRepository.getMinPowerCordLength();
    }
    @GraphQLQuery
    public Double getMaxPowerCordLength(){
        return vacuumCleanerRepository.getMaxPowerCordLength();
    }

    @GraphQLQuery
    public Double getMinVolumeOfDustCollector(){
        return vacuumCleanerRepository.getMinVolumeOfDustCollector();
    }
    @GraphQLQuery
    public Double getMaxVolumeOfDustCollector(){
        return vacuumCleanerRepository.getMaxVolumeOfDustCollector();
    }

    @GraphQLQuery
    public Double getMinWeight(){
        return vacuumCleanerRepository.getMinWeight();
    }
    @GraphQLQuery
    public Double getMaxWeight(){
        return vacuumCleanerRepository.getMaxWeight();
    }
}
