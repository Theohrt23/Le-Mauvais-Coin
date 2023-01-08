package com.epitech.lemauvaiscoin.repository;

import com.epitech.lemauvaiscoin.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findById(Long id);

    boolean existsByMail(String mail);

    boolean existsByUsername(String username);
}
