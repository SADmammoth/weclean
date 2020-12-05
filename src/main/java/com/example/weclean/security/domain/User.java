package com.example.weclean.security.domain;

import com.example.weclean.security.domain.Role;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "appusers")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique=true)
    @Getter
    private Long id;

    @NonNull
    @Column(name = "login", updatable = true, nullable = false, unique=true)
    private String login;
    @NonNull
    private String password;
    @NonNull
    @Enumerated(value = EnumType.STRING)
    private Role role;

    public String getRole(){
        return role.getName();
    }
}
