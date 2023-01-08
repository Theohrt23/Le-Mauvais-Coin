package com.epitech.lemauvaiscoin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.epitech.lemauvaiscoin.domain.Admin;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findById(Long id);

    boolean existsByMail(String mail);

    boolean existsByUsername(String username);

}
