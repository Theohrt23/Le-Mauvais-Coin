package com.epitech.lemauvaiscoin.controller;

import com.epitech.lemauvaiscoin.domain.UserRole;
import com.epitech.lemauvaiscoin.exception.company.CompanyConflictException;
import com.epitech.lemauvaiscoin.exception.company.CompanyNotFoundException;
import com.epitech.lemauvaiscoin.mapper.CompanyMapper;
import com.epitech.lemauvaiscoin.mapper.dto.CompanyDTO;
import com.epitech.lemauvaiscoin.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    private final CompanyMapper companyMapper;

    @GetMapping("/{companyId}")
    @RolesAllowed({UserRole.Values.COMPANY, UserRole.Values.ADMIN})
    public CompanyDTO getCompanyById(@PathVariable(value = "companyId") Long companyId) throws CompanyNotFoundException {
        return  companyMapper.companyToCompanyDto(companyService.getCompanyById(companyId));
    }

    @GetMapping()
    @RolesAllowed({UserRole.Values.COMPANY})
    public List<CompanyDTO> getAllCompany() {
        return companyMapper.companyListToCompanyListDto(companyService.getAllCompany());
    }

    @PostMapping()
    public CompanyDTO createCompany(@Valid @RequestBody CompanyDTO companyDTO) throws CompanyConflictException {
        return companyMapper.companyToCompanyDto(companyService.createCompany(companyMapper.companyDtoToCompany(companyDTO)));
    }

    @PutMapping()
    @RolesAllowed({UserRole.Values.ADMIN, UserRole.Values.COMPANY})
    public CompanyDTO updateCompanyById(@Valid @RequestBody CompanyDTO companyDTO) throws CompanyNotFoundException {
        return companyMapper.companyToCompanyDto(companyService.updateCompanyById(companyMapper.companyDtoToCompany(companyDTO)));
    }

    @DeleteMapping("/{companyId}")
    @RolesAllowed({UserRole.Values.ADMIN})
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteCompanyById(@PathVariable(value = "companyId") Long companyId) throws CompanyNotFoundException {
        companyService.deleteCompanyById(companyId);
    }

}
