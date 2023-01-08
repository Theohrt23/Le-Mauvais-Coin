package com.epitech.lemauvaiscoin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "application")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Long id;

    @Column()
    private Long user_id;

    @Column()
    private Long offer_id;

    @Column()
    private String subject;

    @Column()
    private String body;

    @Column()
    private String name;

    @Column()
    private String surname;

    @Column()
    private String mail;
}
