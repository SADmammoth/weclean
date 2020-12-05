package com.example.weclean.domain;

import com.example.weclean.domain.VacuumCleaner;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Manufacturer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private int id;

    @NonNull
    @Column(name = "name", updatable = true, nullable = false, unique=true)
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<VacuumCleaner> products;
}
