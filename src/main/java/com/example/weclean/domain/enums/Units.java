package com.example.weclean.domain.enums;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

public enum Units {
    volumeOfDustCollector("м³"), price("руб."), powerConsumption("W"), weight("кг"), noiseLevel("dB"), powerCordLength("м");
    public final String value;
    Units(String value){
        this.value = value;
    }
}
