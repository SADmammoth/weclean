package com.example.weclean.domain;

import com.example.weclean.domain.enums.Role;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Getter
    private String id;

    @NonNull
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
