package com.epitech.lemauvaiscoin.mapper;

import com.epitech.lemauvaiscoin.domain.Offer;
import com.epitech.lemauvaiscoin.exception.offer.OfferNotFoundException;
import com.epitech.lemauvaiscoin.service.OfferService;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class UtilMapper {

    @Autowired
    protected OfferService offerService;

    @Autowired
    protected PasswordEncoder encoder;

    @Named("offersToIds")
    public Set<Long> offersToIds(Set<Offer> offers) {
        return offers.stream().map(Offer::getId).collect(Collectors.toSet());
    }

    @Named("idsToOffer")
    public Set<Offer> idsToOffer(Set<Long> idOfOffers) throws OfferNotFoundException {
        Set<Offer> listOffers = new HashSet<>();
        for(Long o : idOfOffers){
            listOffers.add(offerService.getOfferById(o));
        }
        return listOffers;
    }

    @Named("encryptPwd")
    public String encryptPwd(String password) {
        return encoder.encode(password);
    }



}
