package com.example.weclean.domain.enums;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.*;

import javax.persistence.*;

public enum CleaningFeature {
    DRY("dust cleaner"), WASHING("washing");

    private String name;

    CleaningFeature(String name){
        this.name = name;
    }

    @GraphQLQuery
    public String getName(){
        return name;
    }
}
