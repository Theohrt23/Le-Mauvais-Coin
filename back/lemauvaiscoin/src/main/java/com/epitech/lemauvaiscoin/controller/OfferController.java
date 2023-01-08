package com.epitech.lemauvaiscoin.controller;

import com.epitech.lemauvaiscoin.domain.Offer;
import com.epitech.lemauvaiscoin.domain.UserRole;
import com.epitech.lemauvaiscoin.exception.company.CompanyNotFoundException;
import com.epitech.lemauvaiscoin.exception.offer.OfferConflictException;
import com.epitech.lemauvaiscoin.exception.offer.OfferNotFoundException;
import com.epitech.lemauvaiscoin.mapper.OfferMapper;
import com.epitech.lemauvaiscoin.mapper.dto.OfferDTO;
import com.epitech.lemauvaiscoin.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;

    private final OfferMapper offerMapper;

    @GetMapping("/{offerId}")
    public OfferDTO getOfferById(@PathVariable(value = "offerId") Long offerId) throws OfferNotFoundException {
        return offerMapper.offerToOfferDto(offerService.getOfferById(offerId));
    }

    @GetMapping("/getAllOffer/{companyId}")
    public List<OfferDTO> getOfferByCompanyId(@PathVariable(value = "companyId") Long companyId) throws CompanyNotFoundException {
        return offerMapper.offerListToOfferListDto(offerService.getOfferByCompanyId(companyId));
    }

    @GetMapping()
    public List<OfferDTO> getAllOffer() {
        return offerMapper.offerListToOfferListDto(offerService.getAllOffer());
    }

    @PostMapping()
    @RolesAllowed({UserRole.Values.COMPANY,UserRole.Values.ADMIN})
    public OfferDTO createOffer(@Valid @RequestBody OfferDTO offerDTO) throws OfferConflictException {
        return offerMapper.offerToOfferDto(offerService.createOffer(offerMapper.offerDtoToOffer(offerDTO)));
    }

    @PutMapping()
    @RolesAllowed({UserRole.Values.COMPANY,UserRole.Values.ADMIN})
    public OfferDTO updateOfferById(@Valid @RequestBody OfferDTO offerDTO) throws OfferNotFoundException {
        return offerMapper.offerToOfferDto(offerService.updateOfferById(offerMapper.offerDtoToOffer(offerDTO)));
    }

    @DeleteMapping("/{offerId}")
    @RolesAllowed({UserRole.Values.COMPANY,UserRole.Values.ADMIN})
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteOfferById(@PathVariable(value = "offerId") Long offerId) throws OfferNotFoundException {
        offerService.deleteOfferById(offerId);
    }

}
