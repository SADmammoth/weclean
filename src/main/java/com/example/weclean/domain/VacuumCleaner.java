package com.example.weclean.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;
import java.io.Serializable;

@Entity
public class VacuumCleaner implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
private int id;

@Column
    private String model;

@Column
    private String manufacturer;

@Column
@Min(20)
@Max(7000)
    private Float price;

/*@Column
private String construction;

@Column
private String cleaningFeatures;

@Column
    private String dustCollectorType;

@Column
    private Float volumeOfDustCollector;

@Column
    private Integer powerConsumption;

@Column
    private String powerSource;

@Column
    private String color;

@Column
    private Float powerCordLength;

@Column
    private Float weight;

@Column
    private Float noiseLevel;*/


protected VacuumCleaner(){}

public VacuumCleaner(String model, String manufacturer,
                     Float price/*, String construction, String cleaningFeatures,
                     String dustCollectorType, Float volumeOfDustCollector, Integer powerConsumption,
                     String powerSource, String color, Float powerCordLength, Float weight, Float noiseLevel*/){
    this.model = model;
    this.manufacturer = manufacturer;
    this.price=price;
    /*this.construction=construction;
    this.cleaningFeatures =cleaningFeatures;
    this.dustCollectorType = dustCollectorType;
    this.volumeOfDustCollector = volumeOfDustCollector;
    this.powerConsumption = powerConsumption;
    this.powerSource = powerSource;
    this.color=color;
    this.powerCordLength=powerCordLength;
    this.weight=weight;
    this.noiseLevel=noiseLevel;*/
}


    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    /*public String getConstruction() {
        return construction;
    }

    public void setConstruction(String construction) {
        this.construction = construction;
    }

    public String getCleaningFeatures() {
        return cleaningFeatures;
    }

    public void setCleaningFeatures(String cleaningFeatures) {
        this.cleaningFeatures = cleaningFeatures;
    }

    public String getDustCollectorType() {
        return dustCollectorType;
    }

    public void setDustCollectorType(String dustCollectorType) {
        this.dustCollectorType = dustCollectorType;
    }

    public Float getVolumeOfDustCollector() {
        return volumeOfDustCollector;
    }

    public void setVolumeOfDustCollector(Float volumeOfDustCollector) {
        this.volumeOfDustCollector = volumeOfDustCollector;
    }

    public Integer getPowerConsumption() {
        return powerConsumption;
    }

    public void setPowerConsumption(Integer powerConsumption) {
        this.powerConsumption = powerConsumption;
    }

    public String getPowerSource() {
        return powerSource;
    }

    public void setPowerSource(String powerSource) {
        this.powerSource = powerSource;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Float getPowerCordLength() {
        return powerCordLength;
    }

    public void setPowerCordLength(Float powerCordLength) {
        this.powerCordLength = powerCordLength;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getNoiseLevel() {
        return noiseLevel;
    }

    public void setNoiseLevel(Float noiseLevel) {
        this.noiseLevel = noiseLevel;
    }

*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VacuumCleaner vc = (VacuumCleaner) o;
        return Objects.equals(id, vc.id) &&
                Objects.equals(model, vc.model) &&
                Objects.equals(manufacturer, vc.manufacturer) &&
           /*     Objects.equals(construction, vc.construction) &&*/
                Objects.equals(price, vc.price)/* &&
                Objects.equals(cleaningFeatures, vc.cleaningFeatures) &&
                Objects.equals(dustCollectorType, vc.dustCollectorType) &&
                Objects.equals(volumeOfDustCollector, vc.volumeOfDustCollector) &&
                Objects.equals(powerConsumption, vc.powerConsumption) &&
                Objects.equals(powerSource, vc.powerSource) &&
                Objects.equals(color, vc.color) &&
                Objects.equals(powerCordLength, vc.powerCordLength) &&
                Objects.equals(weight, vc.weight) &&
                Objects.equals(noiseLevel, vc.noiseLevel)*/;
    }

    @Override
    public String toString() {
        return "VacuumCleaner{" +
                "id='" + id + '\'' +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", price=" + price +
              /*  ", construction='" + construction + '\'' +
                ", cleaningFeatures='" + cleaningFeatures + '\'' +
                ", dustCollectorType='" + dustCollectorType + '\'' +
                ", volumeOfDustCollector=" + volumeOfDustCollector +
                ", powerConsumption=" + powerConsumption +
                ", powerSource='" + powerSource + '\'' +
                ", color='" + color + '\'' +
                ", powerCordLength=" + powerCordLength +
                ", weight=" + weight +
                ", noiseLevel=" + noiseLevel +*/
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, manufacturer,
                price/*, construction, cleaningFeatures,
                dustCollectorType, volumeOfDustCollector, powerConsumption,
                powerSource, color, powerCordLength, weight, noiseLevel*/);
    }
}
