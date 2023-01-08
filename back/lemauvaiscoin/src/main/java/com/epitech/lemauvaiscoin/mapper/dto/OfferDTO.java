package com.epitech.lemauvaiscoin.mapper.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class OfferDTO {

    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String salary;

    @NotBlank
    private String contract_type;

    @NotBlank
    private String work_period;

    @NotBlank
    private String creation_date;

    private boolean teleworking;

    @NotNull
    private Long company;
}
