package com.epitech.lemauvaiscoin.repository;

import com.epitech.lemauvaiscoin.domain.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Optional<Application> findById(Long id);
}
