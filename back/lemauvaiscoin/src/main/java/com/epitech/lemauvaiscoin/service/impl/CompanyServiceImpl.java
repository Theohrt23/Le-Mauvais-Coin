package com.epitech.lemauvaiscoin.service.impl;

import com.epitech.lemauvaiscoin.domain.Company;
import com.epitech.lemauvaiscoin.exception.company.CompanyConflictException;
import com.epitech.lemauvaiscoin.exception.company.CompanyNotFoundException;
import com.epitech.lemauvaiscoin.repository.CompanyRepository;
import com.epitech.lemauvaiscoin.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public Company createCompany(Company company) throws CompanyConflictException {
        if (companyRepository.existsByUsername(company.getUsername()) || companyRepository.existsByMail(company.getMail())) {
            throw new CompanyConflictException();
        }

        company.setJoinDate(LocalDate.now().toString());

        companyRepository.save(company);
        return company;
    }

    @Override
    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(Long companyId) throws CompanyNotFoundException {
        if (!companyRepository.existsById(companyId)) {
            throw new CompanyNotFoundException();
        }

        return companyRepository.getById(companyId);
    }

    @Override
    public Company updateCompanyById(Company company) throws CompanyNotFoundException {
        if (company.getId() == null || !companyRepository.existsById(company.getId())) {
            throw new CompanyNotFoundException();
        }
        company.setJoinDate(companyRepository.getById(company.getId()).getJoinDate());

        companyRepository.save(company);

        return company;
    }

    @Override
    public void deleteCompanyById(Long companyId) throws CompanyNotFoundException {
        if (!companyRepository.existsById(companyId)) {
            throw new CompanyNotFoundException();
        }

        companyRepository.deleteById(companyId);
    }
}
