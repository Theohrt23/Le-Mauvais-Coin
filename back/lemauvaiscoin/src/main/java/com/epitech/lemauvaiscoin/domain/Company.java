package com.epitech.lemauvaiscoin.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue(UserRole.Values.COMPANY)
public class Company extends User{

    @NonNull
    @Column()
    @Size(max = 120)
    private String city;

    @NonNull
    @Column()
    @Size(max = 120)
    private String zip_code;

    @NonNull
    @Column()
    @Size(max = 120)
    private String address;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            mappedBy= "company"
    )
    private Set<Offer> offerList;

    public Company(String name, String surname, String mail, String username, String password, String joinDate, String city, String ZIPCode, String address){
        super(name,surname,mail,username,password,joinDate);
        this.city = city;
        this.zip_code = ZIPCode;
        this.address = address;
    }
}
