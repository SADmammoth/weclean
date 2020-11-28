package com.example.weclean.domain.enums;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

public enum DustCollectorType {
    BAG("bag"), CONTAINER("container");

    private String name;

    DustCollectorType(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
