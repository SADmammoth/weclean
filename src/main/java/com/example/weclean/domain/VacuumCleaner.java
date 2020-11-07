package com.example.weclean.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import io.leangen.graphql.annotations.*;
import io.leangen.graphql.annotations.types.GraphQLType;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Null;
import java.util.Objects;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class VacuumCleaner implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private int id;

    public ImagesList getImagesList() {
        return new ImagesList(id);
    }

    @Column
    @NonNull
    private String model;

    @Column
    @NonNull
    private String manufacturer;

    @Column
    @NonNull
    private Double price;
/*
    @Column
    @NonNull
    private String construction;

    @Column
    @NonNull
    private String cleaningFeatures;

    @Column
    @NonNull
    private String dustCollectorType;

    @Column
    @NonNull
    private Double volumeOfDustCollector;

    @Column
    @NonNull
    private Integer powerConsumption;

    @Column
    @NonNull
    private String powerSource;

    @Column
    @NonNull
    private String color;

    @Column
    @NonNull
    private Double powerCordLength;

    @Column
    @NonNull
    private Double weight;

    @Column
    @NonNull
    private Double noiseLevel;*/

}
