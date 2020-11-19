package com.example.weclean.domain.enums;

import io.leangen.graphql.annotations.GraphQLQuery;

public enum DustCollectorType {
    BAG("bag"), CONTAINER("container");

    private String name;

    DustCollectorType(String name){
        this.name = name;
    }

    @GraphQLQuery
    public String getName(){
        return name;
    }
}
