package com.epitech.lemauvaiscoin.mapper.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
public class CompanyDTO extends UserDTO{

    @NotBlank
    @Size(max = 120)
    private String city;

    @NotBlank
    @Size(max = 5)
    private String zip_code;

    @NotBlank
    @Size(max = 120)
    private String address;

    private Set<Long> offers;

}
