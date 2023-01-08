package com.epitech.lemauvaiscoin.mapper.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ApplicationDTO {

    private Long id;

    private Long user_id;

    @NotNull
    private Long offer_id;


    @NotBlank
    private String subject;

    @NotBlank
    private String body;

    private String surname;

    private String name;

    private String mail;
}
