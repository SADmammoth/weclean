package com.example.weclean.domain.enums;

import io.leangen.graphql.annotations.GraphQLQuery;

public enum PowerSource {
    BATTERY("battery"), NETWORK("network"), LIGHTER("cigarette lighter");

    private String name;

    PowerSource(String name){
        this.name = name;
    }

    @GraphQLQuery
    public String getName(){
        return name;
    }
}
