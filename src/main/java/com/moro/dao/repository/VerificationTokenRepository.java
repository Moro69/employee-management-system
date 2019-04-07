package com.moro.dao.repository;

import com.moro.model.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {

    Optional<VerificationToken> findByUserEmail(String email);
    Optional<VerificationToken> findByToken(String token);
}
