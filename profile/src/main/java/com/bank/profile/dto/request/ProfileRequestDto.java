package com.bank.profile.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO для профиля клиента.
 */
@Data
public class ProfileRequestDto {

    @Schema(description = "Номер телефона без +7", example = "9001234567")
    @NotNull
    private Long phoneNumber;

    @Schema(description = "Электронная почта")
    @Email
    @Size(max = 264)
    private String email;

    @Schema(description = "Имя на карте")
    @Size(max = 370)
    private String nameOnCard;

    @Schema(description = "ИНН")
    private Long inn;

    @Schema(description = "СНИЛС")
    private Long snils;

    @Schema(description = "Идентификатор паспорта")
    @NotNull
    private Long passportId;

    @Schema(description = "Идентификатор фактической регистрации")
    private Long actualRegistrationId;
}