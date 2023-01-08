package com.epitech.lemauvaiscoin.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Table(name = "user")
@Data
@NoArgsConstructor
@SuperBuilder
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role")
@DiscriminatorValue(UserRole.Values.USER)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Long id;

    @NonNull
    @Column()
    private String name;

    @NonNull
    @Column()
    private String surname;

    @NonNull
    @Column()
    private String mail;

    @NonNull
    @Size(max = 20)
    @Column()
    private String username;

    @NonNull
    @Column()
    @Size(max = 120)
    private String password;

    @Column()
    private String joinDate;

    @Column(name = "role", insertable = false, updatable = false)
    private String role;

    public User (String name, String surname, String mail, String username, String password, String joinDate){
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.username = username;
        this.password = password;
        this.joinDate = joinDate;
    }
}
