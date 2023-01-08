package com.epitech.lemauvaiscoin.service;

import com.epitech.lemauvaiscoin.domain.Application;
import com.epitech.lemauvaiscoin.exception.application.ApplicationNotFoundException;
import com.epitech.lemauvaiscoin.exception.company.CompanyNotFoundException;
import com.epitech.lemauvaiscoin.exception.offer.OfferNotFoundException;
import com.epitech.lemauvaiscoin.exception.user.UserNotFoundException;

import java.util.List;

public interface ApplicationService {

    Application createApplication(Application application) throws UserNotFoundException, CompanyNotFoundException, OfferNotFoundException;

    List<Application> getAllApplication();

    Application getApplicationById(Long applicationId) throws ApplicationNotFoundException;

    Application updateApplicationById(Application application) throws ApplicationNotFoundException;

    void deleteApplicationById(Long applicationId) throws ApplicationNotFoundException;
}
