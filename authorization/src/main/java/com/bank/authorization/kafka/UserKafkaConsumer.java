package com.bank.authorization.kafka;

import com.bank.authorization.constants.OperationEnum;
import com.bank.authorization.dto.KafkaMessage;
import com.bank.authorization.dto.UserDto;
import com.bank.authorization.entity.User;
import com.bank.authorization.exeptionhandler.EntityNotFoundException;
import com.bank.authorization.exeptionhandler.ValidationException;
import com.bank.authorization.mapper.UserMapper;
import com.bank.authorization.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserKafkaConsumer {

    private final UserService userService;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final UserMapper userMapper;

    @Value("${spring.kafka.topics.user-response}")
    private String userResponseTopic;

    @KafkaListener(topics = "${spring.kafka.topics.user-create}")
    public void consumeUserCreate(@Payload KafkaMessage message) {
        log.info("Processing user.create request. CorrelationId: {}", message.getCorrelationId());

        if (!userService.hasAdminRole(message.getToken())) {
            log.warn("Unauthorized attempt to create user. CorrelationId: {}, Token: {}",
                    message.getCorrelationId(), message.getToken());
            throw new ValidationException("Only users with ADMIN role can perform this operation");
        }

        UserDto userDto = message.getUserData();
        User user = userMapper.toUser(userDto);

        User createdUser = userService.createUser(user);
        UserDto responseDto = userMapper.toDto(createdUser);
        sendUserResponse(message.getCorrelationId(), responseDto, OperationEnum.CREATE);
    }

    @KafkaListener(topics = "${spring.kafka.topics.user-get}")
    public void consumeUserGet(@Payload KafkaMessage message) throws EntityNotFoundException {
        log.info("Processing user.get request. CorrelationId: {}", message.getCorrelationId());

        if (!userService.hasAdminRole(message.getToken())) {
            log.warn("Unauthorized attempt to get user. CorrelationId: {}, Token: {}",
                    message.getCorrelationId(), message.getToken());
            throw new ValidationException("Only users with ADMIN role can perform this operation");
        }

        User user = userService.getUserById(message.getUserData().getId());
        log.info("User retrieved successfully. Id: {}, CorrelationId: {}",
                message.getUserData().getId(), message.getCorrelationId());

        UserDto userDto = userMapper.toDto(user);
        sendUserResponse(message.getCorrelationId(), userDto, OperationEnum.GET);
    }

    @KafkaListener(topics = "${spring.kafka.topics.user-delete}")
    public void consumeUserDelete(@Payload KafkaMessage message) throws EntityNotFoundException {
        log.info("Processing user.delete request. CorrelationId: {}", message.getCorrelationId());

        if (!userService.hasAdminRole(message.getToken())) {
            log.warn("Unauthorized attempt to delete user. CorrelationId: {}, Token: {}",
                    message.getCorrelationId(), message.getToken());
            throw new ValidationException("Only users with ADMIN role can perform this operation");
        }

        userService.deleteUser(message.getId());
        sendUserResponse(message.getCorrelationId(), null, OperationEnum.DELETE);
    }

    @KafkaListener(topics = "${spring.kafka.topics.user-update}")
    public void consumeUserUpdate(@Payload KafkaMessage message) throws EntityNotFoundException {
        log.info("Processing user.update request. CorrelationId: {}", message.getCorrelationId());

        if (!userService.hasAdminRole(message.getToken())) {
            log.warn("Unauthorized attempt to update user. CorrelationId: {}, Token: {}",
                    message.getCorrelationId(), message.getToken());
            throw new ValidationException("Only users with ADMIN role can perform this operation");
        }

        UserDto userData = message.getUserData();
        User updatedUser = userService.updateUser(
                userData.getId(),
                userData.getProfileId(),
                userData.getRole(),
                userData.getPassword()
        );

        UserDto userDto = userMapper.toDto(updatedUser);
        sendUserResponse(message.getCorrelationId(), userDto, OperationEnum.UPDATE);
    }

    private void sendUserResponse(String correlationId, UserDto userData, OperationEnum operation) {
        KafkaMessage response = new KafkaMessage();
        response.setCorrelationId(correlationId);
        response.setUserData(userData);
        response.setOperation(operation);
        kafkaTemplate.send(userResponseTopic, response);
    }
}
