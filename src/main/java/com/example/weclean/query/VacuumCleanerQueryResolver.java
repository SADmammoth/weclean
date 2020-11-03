package com.example.weclean.query;

import graphql.kickstart.tools.GraphQLQueryResolver;
import com.example.weclean.domain.VacuumCleaner;
import com.example.weclean.service.VacuumCleanerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class VacuumCleanerQueryResolver implements GraphQLQueryResolver {
    @Autowired
    private VacuumCleanerService vacuumCleanerService;
    public List<VacuumCleaner> vacuumCleaners(final int count) {
        return this.vacuumCleanerService.getAllVacuumCleaners(count);
    }
    public Optional<VacuumCleaner> vacuumCleaner(final int id) {
        return this.vacuumCleanerService.getVacuumCleaner(id);
    }
}