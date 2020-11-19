package com.example.weclean.domain.enums;

import io.leangen.graphql.annotations.GraphQLQuery;

public enum Construction {
    UPRIGHT("upright"), HANDHELD("handheld"), VERTICAL("vertical"), AUTONOMOUS("autonomous");

    private String name;

    Construction(String name){
        this.name = name;
    }

    @GraphQLQuery
    public String getName(){
        return name;
    }
}
