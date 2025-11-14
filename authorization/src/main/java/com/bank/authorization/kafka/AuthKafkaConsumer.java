package com.bank.authorization.kafka;

import com.bank.authorization.constants.OperationEnum;
import com.bank.authorization.dto.KafkaMessage;
import com.bank.authorization.entity.User;
import com.bank.authorization.exeptionhandler.EntityNotFoundException;
import com.bank.authorization.service.JWTService;
import com.bank.authorization.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.bank.authorization.constants.MessageEnum.LOGIN_FAILED;
import static com.bank.authorization.constants.MessageEnum.LOGIN_SUCCESS;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthKafkaConsumer {

    private final JWTService jwtService;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final UserService userService;

    @Value("${spring.kafka.topics.auth-response}")
    private String authResponseTopic;

    @KafkaListener(topics = "${spring.kafka.topics.auth-login}")
    public void consumeAuthLogin(@Payload KafkaMessage message) throws EntityNotFoundException {
        log.info("Received login request for profileId: {}, correlationId: {}",
                message.getProfileId(), message.getCorrelationId());

        User user = userService.getUserByProfileId(message.getProfileId());

        boolean isAuthenticated = jwtService.verifyCredentials(
                message.getProfileId(),
                message.getPassword()
        );

        log.info("Authentication result for profileId {}: {}",
                message.getProfileId(), isAuthenticated ? "SUCCESS" : "FAILED");

        KafkaMessage response = new KafkaMessage();
        if (isAuthenticated) {
            String token = jwtService.generateToken(message.getProfileId(), user.getRole());
            log.info("Generated token for profileId: {}", message.getProfileId());

            response.setToken(token);
            response.setProfileId(message.getProfileId());
            response.setValid(true);
            response.setMessage(LOGIN_SUCCESS);
            log.info("Login successful for profileId: {}, correlationId: {}",
                    message.getProfileId(), message.getCorrelationId());
        } else {
            response.setValid(false);
            response.setMessage(LOGIN_FAILED);
            log.warn("Login failed for profileId: {}, correlationId: {}",
                    message.getProfileId(), message.getCorrelationId());
        }

        sendAuthResponse(message.getCorrelationId(), response, OperationEnum.LOGIN);
    }

    @KafkaListener(topics = "${spring.kafka.topics.auth-validate}")
    public void consumeAuthValidate(@Payload KafkaMessage message) {
        log.info("Received token validation request, correlationId: {}, token present: {}",
                message.getCorrelationId(), message.getToken() != null);

        KafkaMessage response = jwtService.validateTokenAndCreateResponse(message.getToken());
        log.info("Token validation result: valid={}, message={}",
                response.getValid(), response.getMessage());

        sendAuthResponse(message.getCorrelationId(), response, OperationEnum.VALIDATE);
    }


    private void sendAuthResponse(String correlationId, KafkaMessage authData, OperationEnum operation) {
        log.info("Sending auth response to topic 'auth.response', correlationId: {}, operation: {}",
                correlationId, operation);

        authData.setCorrelationId(correlationId);
        authData.setOperation(operation);

        try {
            kafkaTemplate.send(authResponseTopic, authData);
            log.info("Successfully sent auth response for correlationId: {}", correlationId);
        } catch (Exception e) {
            log.error("Failed to send auth response to Kafka, correlationId: {}, error: {}",
                    correlationId, e.getMessage(), e);
        }
    }
}
