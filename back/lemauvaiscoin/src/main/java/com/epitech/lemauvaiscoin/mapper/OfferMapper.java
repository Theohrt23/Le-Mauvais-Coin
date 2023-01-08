package com.epitech.lemauvaiscoin.mapper;

import com.epitech.lemauvaiscoin.domain.Company;
import com.epitech.lemauvaiscoin.domain.Offer;
import com.epitech.lemauvaiscoin.exception.company.CompanyNotFoundException;
import com.epitech.lemauvaiscoin.mapper.dto.CompanyDTO;
import com.epitech.lemauvaiscoin.mapper.dto.OfferDTO;
import com.epitech.lemauvaiscoin.service.CompanyService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class OfferMapper {

    @Autowired
    protected CompanyService companyService;

    @Named("companyToIds")
    public Long companyToIds(Company company) {
        return company.getId();
    }

    @Named("idsToCompany")
    public Company idsToCompany(Long companyId) throws CompanyNotFoundException {
        return companyService.getCompanyById(companyId);
    }

    @Mapping(target = "company", source = "company", qualifiedByName = "companyToIds")
    public abstract OfferDTO offerToOfferDto(Offer offer);

    @Mapping(target = "company", source = "company", qualifiedByName = "idsToCompany")
    public abstract Offer offerDtoToOffer(OfferDTO offerDTO);

    public abstract List<OfferDTO> offerListToOfferListDto(List<Offer> offerList);

}
