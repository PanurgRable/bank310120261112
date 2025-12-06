package com.bank.profile.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * DTO ответа по профилю клиента.
 */
@Data
public class ProfileResponseDto {

    @Schema(description = "Идентификатор профиля")
    private Long id;

    private Long phoneNumber;
    private String email;
    private String nameOnCard;
    private Long inn;
    private Long snils;
    private Long passportId;
    private Long actualRegistrationId;
}