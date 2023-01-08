package com.epitech.lemauvaiscoin.service.impl;

import com.epitech.lemauvaiscoin.domain.Application;
import com.epitech.lemauvaiscoin.exception.application.ApplicationNotFoundException;
import com.epitech.lemauvaiscoin.exception.company.CompanyNotFoundException;
import com.epitech.lemauvaiscoin.exception.offer.OfferNotFoundException;
import com.epitech.lemauvaiscoin.exception.user.UserNotFoundException;
import com.epitech.lemauvaiscoin.repository.ApplicationRepository;
import com.epitech.lemauvaiscoin.service.ApplicationService;
import com.epitech.lemauvaiscoin.service.CompanyService;
import com.epitech.lemauvaiscoin.service.OfferService;
import com.epitech.lemauvaiscoin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    private final CompanyService companyService;

    private final UserService userService;

    private final JavaMailSender mailSender;

    private final OfferService offerService;

    public void sendEmail(String subject, String body, Long companyId, Long userId, String mail, String name, String surname) throws CompanyNotFoundException, UserNotFoundException {
        SimpleMailMessage message = new SimpleMailMessage();
        if (mail == null) {
            message.setFrom(userService.getUserById(userId).getMail());
            message.setText(body + "\n\nContact:\n" + userService.getUserById(userId).getMail() + "\n" + userService.getUserById(userId).getName() + " " + userService.getUserById(userId).getSurname());
        }else {
            message.setFrom(mail);
            message.setText(body + "\n\nContact:\n" + mail + "\n" + name + " " + surname);
        }
        message.setTo(companyService.getCompanyById(companyId).getMail());
        message.setSubject(subject);
        mailSender.send(message);
    }

    @Override
    public Application createApplication(Application application) throws UserNotFoundException, CompanyNotFoundException, OfferNotFoundException {
        applicationRepository.save(application);

        sendEmail(application.getSubject(), application.getBody(), offerService.getOfferById(application.getOffer_id()).getCompany().getId(), application.getUser_id(), application.getMail(), application.getName(),application.getSurname());

        return application;
    }

    @Override
    public List<Application> getAllApplication() {
        return applicationRepository.findAll();
    }

    @Override
    public Application getApplicationById(Long applicationId) throws ApplicationNotFoundException {
        if (!applicationRepository.existsById(applicationId)) {
            throw new ApplicationNotFoundException();
        }

        return applicationRepository.getById(applicationId);
    }

    @Override
    public Application updateApplicationById(Application application) throws ApplicationNotFoundException {
        if (application.getId() == null || !applicationRepository.existsById(application.getId())) {
            throw new ApplicationNotFoundException();
        }
        applicationRepository.save(application);

        return application;
    }

    @Override
    public void deleteApplicationById(Long applicationId) throws ApplicationNotFoundException {
        if (!applicationRepository.existsById(applicationId)) {
            throw new ApplicationNotFoundException();
        }

        applicationRepository.deleteById(applicationId);
    }
}
