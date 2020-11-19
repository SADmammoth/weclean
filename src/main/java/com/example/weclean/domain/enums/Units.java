package com.example.weclean.domain.enums;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

public enum Units {
    volumeOfDustCollector("m³"), price("USD"), powerConsumption("W"), weight("kg"), noiseLevel("dB"), powerCordLength("m");
    public final String value;
    Units(String value){
        this.value = value;
    }
}
