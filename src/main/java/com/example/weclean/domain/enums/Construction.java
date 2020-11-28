package com.example.weclean.domain.enums;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

public enum Construction {
    UPRIGHT("upright"), HANDHELD("handheld"), VERTICAL("vertical"), AUTONOMOUS("autonomous");

    private String name;

    Construction(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
