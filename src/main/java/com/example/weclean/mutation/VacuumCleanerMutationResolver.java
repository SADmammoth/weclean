package com.example.weclean.mutation;

import com.example.weclean.domain.VacuumCleaner;
import com.example.weclean.service.VacuumCleanerService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VacuumCleanerMutationResolver implements GraphQLMutationResolver {
    @Autowired
    private VacuumCleanerService vacuumCleanerService;
    public VacuumCleaner createVacuumCleaner(final String model, final String manufacturer,
                                             final Float price) {
        return this.vacuumCleanerService.createVacuumCleaner(model, manufacturer, price);
    }
}