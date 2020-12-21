package com.example.weclean.domain.enums;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

public enum DustCollectorType {
    BAG("мешок"), CONTAINER("контейнер");

    private String name;

    DustCollectorType(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
