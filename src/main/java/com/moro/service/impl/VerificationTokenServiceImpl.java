package com.moro.service.impl;

import com.moro.dao.repository.VerificationTokenRepository;
import com.moro.model.entity.VerificationToken;
import com.moro.model.exception.EntityNotFoundException;
import com.moro.service.VerificationTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private final VerificationTokenRepository tokenRepository;

    @Autowired
    public VerificationTokenServiceImpl(final VerificationTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public VerificationToken saveToken(final VerificationToken verificationToken) {
        log.info("Saving token");

        return tokenRepository.saveAndFlush(verificationToken);
    }

    @Override
    public VerificationToken findByToken(final String token) {
        log.info("Finding token {}", token);

        return tokenRepository.findByToken(token).orElseThrow(
                () -> new EntityNotFoundException(
                String.format("No token %s", token)));
    }

    @Override
    public VerificationToken findByUserEmail(final String email) {
        log.info("Finding token by user email {}", email);

        return tokenRepository.findByUserEmail(email).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("No token related to user with email %s", email)));
    }
}
