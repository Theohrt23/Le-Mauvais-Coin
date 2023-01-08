package com.epitech.lemauvaiscoin.repository;

import com.epitech.lemauvaiscoin.domain.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    Optional<Offer> findById(Long id);

    boolean existsByTitle(String title);

}
