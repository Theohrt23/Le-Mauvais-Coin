package com.epitech.lemauvaiscoin.service;

import com.epitech.lemauvaiscoin.domain.Company;
import com.epitech.lemauvaiscoin.exception.company.CompanyConflictException;
import com.epitech.lemauvaiscoin.exception.company.CompanyNotFoundException;

import java.util.List;

public interface CompanyService {

    Company createCompany(Company company) throws CompanyConflictException;

    List<Company> getAllCompany();

    Company getCompanyById(Long companyId) throws CompanyNotFoundException;

    Company updateCompanyById(Company company) throws CompanyNotFoundException;

    void deleteCompanyById(Long companyId) throws CompanyNotFoundException;
}
