package com.example.weclean.service;

import com.example.weclean.domain.Manufacturer;
import com.example.weclean.domain.VacuumCleaner;
import com.example.weclean.repo.ManufacturerRepository;
import com.example.weclean.repo.VacuumCleanerRepository;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@GraphQLApi
public class ManufacturerService {
    @Autowired
    ManufacturerRepository manufacturerRepository;

    public Manufacturer createManufacturer(String name){
        Optional<Manufacturer> found = manufacturerRepository.findByName(name);
        return found.isPresent() ? found.get() : manufacturerRepository.save(new Manufacturer(name));
    }

    @Transactional(readOnly = true)
    @GraphQLQuery
    public List<Manufacturer> getAllManufacturers() {
        return StreamSupport.stream(this.manufacturerRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @GraphQLQuery
    public Boolean exists(final String name){
        return this.manufacturerRepository.existsByName(name);
    }

    public Optional<Manufacturer> findByName(String name){
        return this.manufacturerRepository.findByName(name);
    }
}
