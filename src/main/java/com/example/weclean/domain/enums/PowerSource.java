package com.example.weclean.domain.enums;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

public enum PowerSource {
    BATTERY("battery"), NETWORK("network"), LIGHTER("cigarette lighter");

    private String name;

    PowerSource(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
