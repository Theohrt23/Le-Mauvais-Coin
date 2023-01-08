package com.epitech.lemauvaiscoin.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue(UserRole.Values.ADMIN)
public class Admin extends User{

    public Admin(String name, String surname, String mail, String username, String password, String joinDate){
        super(name,surname,mail,username,password,joinDate);
    }
}
