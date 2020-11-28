package com.example.weclean.domain;

import com.example.weclean.domain.enums.*;
import com.example.weclean.domain.enums.CleaningFeature;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

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

    @NonNull
    @ManyToOne
    private Manufacturer manufacturer;

    public void setManufacturer(Manufacturer manufacturer){
        this.manufacturer = manufacturer;
    }

    @GraphQLQuery
    public String getManufacturer() {
        if(manufacturer == null){
            return null;
        }
        return manufacturer.getName();
    }

    @Column
    @NonNull
    private Double price;

    @Column
    @Enumerated(EnumType.STRING)
    @NonNull
    private Construction construction;

    @GraphQLQuery
    public String getConstructionName() {
        return construction.getName();
    }

    @Column
    @NonNull
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER, targetClass = CleaningFeature.class)
    private Set<CleaningFeature> cleaningFeatures;

    @GraphQLQuery
    public Set<String> getCleaningFeatures() {
        if(cleaningFeatures == null){
            return  null;
        }
        return cleaningFeatures.stream().map(feature->feature.getName()).collect(Collectors.toSet());
    }

    @Column
    @NonNull
    private Boolean hasFilter;

    @Column
    @NonNull
    @Enumerated(EnumType.STRING)
    private DustCollectorType dustCollectorType;

    @GraphQLQuery
    public String getDustCollectorTypeName() {
        return dustCollectorType.getName();
    }

    @Column
    @NonNull
    private Double volumeOfDustCollector;

    @Column
    @NonNull
    private Double powerConsumption;

    @Column
    @NonNull
    @Enumerated(EnumType.STRING)
    private PowerSource powerSource;

    @GraphQLQuery
    public String getPowerSourceName() {
        return powerSource.getName();
    }

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
    private Double noiseLevel;

    @Column
    @NonNull
    private Double discount;

    public Double getOldPrice(){
       return this.price * discount / 100 + this.price;
    }
}
