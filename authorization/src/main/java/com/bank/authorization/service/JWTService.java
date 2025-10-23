package com.bank.authorization.service;

import com.bank.authorization.constants.RoleEnum;
import com.bank.authorization.dto.KafkaMessage;

public interface JWTService {
    String generateToken(Long profileId, RoleEnum role);

    KafkaMessage validateTokenAndCreateResponse(String token);

    String extractRoleFromToken(String token);

    boolean verifyCredentials(Long profileId, String password);
}
