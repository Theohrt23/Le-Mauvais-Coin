package com.epitech.lemauvaiscoin.service.impl;

import com.epitech.lemauvaiscoin.domain.Company;
import com.epitech.lemauvaiscoin.domain.Offer;
import com.epitech.lemauvaiscoin.exception.company.CompanyNotFoundException;
import com.epitech.lemauvaiscoin.exception.offer.OfferConflictException;
import com.epitech.lemauvaiscoin.exception.offer.OfferNotFoundException;
import com.epitech.lemauvaiscoin.repository.CompanyRepository;
import com.epitech.lemauvaiscoin.repository.OfferRepository;
import com.epitech.lemauvaiscoin.service.CompanyService;
import com.epitech.lemauvaiscoin.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    private final CompanyService companyService;

    private final CompanyRepository companyRepository;

    @Override
    public Offer createOffer(Offer offer) throws OfferConflictException {
        if (offerRepository.existsByTitle(offer.getTitle())){
            throw new OfferConflictException();
        }
        offerRepository.save(offer);

        Set<Offer> offers = offer.getCompany().getOfferList();
        offers.add(offer);
        Company company = offer.getCompany();
        companyRepository.save(company);

        return offer;
    }

    @Override
    public List<Offer> getAllOffer() {
        return offerRepository.findAll();
    }

    @Override
    public Offer getOfferById(Long offerId) throws OfferNotFoundException {
        if (!offerRepository.existsById(offerId)) {
            throw new OfferNotFoundException();
        }

        return offerRepository.getById(offerId);
    }

    @Override
    public Offer updateOfferById(Offer offer) throws OfferNotFoundException {
        if (offer.getId() == null || !offerRepository.existsById(offer.getId())) {
            throw new OfferNotFoundException();
        }
        offerRepository.save(offer);

        return offer;
    }

    @Override
    public void deleteOfferById(Long offerId) throws OfferNotFoundException {
        if (!offerRepository.existsById(offerId)) {
            throw new OfferNotFoundException();
        }

        offerRepository.deleteById(offerId);
    }

    @Override
    public List<Offer> getOfferByCompanyId(Long companyId) {
        List<Offer> offerList = new ArrayList<>();
        for (Offer o : offerRepository.findAll()){
            if (o.getCompany().getId() == companyId)
                offerList.add(o);
        }
        return offerList;
    }
}
