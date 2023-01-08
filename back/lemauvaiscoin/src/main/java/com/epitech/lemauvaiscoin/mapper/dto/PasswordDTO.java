package com.epitech.lemauvaiscoin.mapper.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PasswordDTO {

    @NotNull
    private Long user_id;

    @NotBlank
    private String password;

}
