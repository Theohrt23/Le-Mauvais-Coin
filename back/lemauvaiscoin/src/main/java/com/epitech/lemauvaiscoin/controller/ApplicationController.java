package com.epitech.lemauvaiscoin.controller;

import com.epitech.lemauvaiscoin.domain.UserRole;
import com.epitech.lemauvaiscoin.exception.application.ApplicationNotFoundException;
import com.epitech.lemauvaiscoin.exception.company.CompanyNotFoundException;
import com.epitech.lemauvaiscoin.exception.offer.OfferNotFoundException;
import com.epitech.lemauvaiscoin.exception.user.UserNotFoundException;
import com.epitech.lemauvaiscoin.mapper.ApplicationMapper;
import com.epitech.lemauvaiscoin.mapper.dto.ApplicationDTO;
import com.epitech.lemauvaiscoin.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/application")
public class ApplicationController {

    private final ApplicationService applicationService;

    private final ApplicationMapper applicationMapper;

    @GetMapping("/{applicationId}")
    @RolesAllowed({UserRole.Values.ADMIN})
    public ApplicationDTO getApplicationById(@PathVariable(value = "applicationId") Long applicationId) throws ApplicationNotFoundException {
        return  applicationMapper.applicationToApplicationDto(applicationService.getApplicationById(applicationId));
    }

    @GetMapping()
    @RolesAllowed({UserRole.Values.ADMIN})
    public List<ApplicationDTO> getAllApplication() {
        return applicationMapper.applicationListToApplicationListDto(applicationService.getAllApplication());
    }

    @PostMapping()
    public ApplicationDTO createApplication(@Valid @RequestBody ApplicationDTO applicationDTO) throws UserNotFoundException, CompanyNotFoundException, OfferNotFoundException {
        return applicationMapper.applicationToApplicationDto(applicationService.createApplication(applicationMapper.applicationDtoToApplication(applicationDTO)));
    }

    @PutMapping()
    @RolesAllowed({UserRole.Values.ADMIN})
    public ApplicationDTO updateApplicationById(@Valid @RequestBody ApplicationDTO applicationDTO) throws ApplicationNotFoundException {
        return applicationMapper.applicationToApplicationDto(applicationService.updateApplicationById(applicationMapper.applicationDtoToApplication(applicationDTO)));
    }

    @DeleteMapping("/{applicationId}")
    @RolesAllowed({UserRole.Values.ADMIN})
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteApplicationById(@PathVariable(value = "applicationId") Long applicationId) throws ApplicationNotFoundException {
        applicationService.deleteApplicationById(applicationId);
    }
}
