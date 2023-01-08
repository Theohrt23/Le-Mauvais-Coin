package com.epitech.lemauvaiscoin.mapper;

import com.epitech.lemauvaiscoin.domain.Company;
import com.epitech.lemauvaiscoin.mapper.dto.CompanyDTO;
import com.epitech.lemauvaiscoin.service.CompanyService;
import com.epitech.lemauvaiscoin.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CompanyMapper extends UtilMapper{

    @Mapping(target = "offers", source = "offerList", qualifiedByName = "offersToIds")
    public abstract CompanyDTO companyToCompanyDto(Company company);

    @Mapping(target = "offerList", source = "offers", qualifiedByName = "idsToOffer")
    @Mapping(target = "password", source = "password", qualifiedByName = "encryptPwd")
    public abstract Company companyDtoToCompany(CompanyDTO companyDTO);

    public abstract List<CompanyDTO> companyListToCompanyListDto(List<Company> companyList);

}
