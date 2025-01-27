package com.example.weclean.domain.enums;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

public enum CleaningFeature {
    DRY("dust cleaner"), WASHING("washing");

    private String name;

    CleaningFeature(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
