package com.moro.service;

import com.moro.model.entity.VerificationToken;

public interface VerificationTokenService {

    VerificationToken saveToken(VerificationToken verificationToken);

    VerificationToken findByToken(String token);

    VerificationToken findByUserEmail(String email);
}
