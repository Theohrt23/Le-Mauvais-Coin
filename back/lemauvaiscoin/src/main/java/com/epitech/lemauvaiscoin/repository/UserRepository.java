package com.epitech.lemauvaiscoin.repository;

import com.epitech.lemauvaiscoin.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    boolean existsByMail(String mail);

    boolean existsByUsername(String username);

}
