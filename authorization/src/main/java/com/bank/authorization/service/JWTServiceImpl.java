package com.bank.authorization.service;

import com.bank.authorization.constants.RoleEnum;
import com.bank.authorization.dto.KafkaMessage;
import com.bank.authorization.entity.User;
import com.bank.authorization.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class JWTServiceImpl implements JWTService {

    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.expiration}")
    private long expiration;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private SecretKey getSigningKey() {
        log.debug("Generating signing key from secret");
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    @Override
    public String generateToken(Long profileId, RoleEnum role) {
        log.info("Generating JWT token for profile ID: {}", profileId);

        try {
            String token = Jwts.builder()
                    .setSubject(profileId.toString())
                    .claim("role", role.toString())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + expiration))
                    .signWith(getSigningKey())
                    .compact();
            log.debug("JWT token generated for profile ID: {}, expiration: {} ms", profileId, expiration);

            return token;
        } catch (Exception e) {
            log.error("Error generating JWT token for profile ID: {}", profileId, e);
            throw e;
        }
    }

    @Override
    public KafkaMessage validateTokenAndCreateResponse(String token) {
        log.info("Validating JWT token");

        KafkaMessage response = new KafkaMessage();

        if (token == null || token.trim().isEmpty()) {
            log.error("JWT token is null or empty");
            response.setValid(false);
            response.setProfileId(null);
            response.setMessage("INVALID_TOKEN");
            return response;
        }

        try {
            log.info("Parsing JWT token claims");
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            Long profileId = Long.parseLong(claims.getSubject());
            log.debug("Successfully extracted profile ID from token: {}", profileId);

            response.setValid(true);
            response.setProfileId(profileId);
            response.setMessage("VALID_TOKEN");
            log.debug("Successfully extracted profile ID from token: {}", profileId);

        } catch (Exception e) {
            log.error("JWT token validation failed: {}", e.getMessage());
            response.setValid(false);
            response.setProfileId(null);
            response.setMessage("INVALID_TOKEN");
        }

        return response;
    }

    @Override
    public String extractRoleFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.get("role", String.class);
    }

    @Override
    public boolean verifyCredentials(Long profileId, String password) {
        User user = userRepository.findByProfileId(profileId)
                .orElse(null);

        return user != null && passwordEncoder.matches(password, user.getPassword());
    }
}
