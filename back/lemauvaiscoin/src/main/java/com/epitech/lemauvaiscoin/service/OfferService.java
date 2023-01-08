package com.epitech.lemauvaiscoin.service;

import com.epitech.lemauvaiscoin.domain.Offer;
import com.epitech.lemauvaiscoin.domain.User;
import com.epitech.lemauvaiscoin.exception.company.CompanyNotFoundException;
import com.epitech.lemauvaiscoin.exception.offer.OfferConflictException;
import com.epitech.lemauvaiscoin.exception.offer.OfferNotFoundException;

import java.util.List;
import java.util.Set;

public interface OfferService {

    Offer createOffer(Offer offer) throws OfferConflictException;

    List<Offer> getAllOffer();

    Offer getOfferById(Long offerId) throws OfferNotFoundException;

    Offer updateOfferById(Offer offer) throws OfferNotFoundException;

    void deleteOfferById(Long offerId) throws OfferNotFoundException;

    List<Offer> getOfferByCompanyId(Long companyId) throws CompanyNotFoundException;

}
