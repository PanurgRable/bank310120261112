package com.bank.profile.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * DTO для привязки профиля к счёту.
 */
@Data
public class AccountDetailsIdRequestDto {

    @Schema(description = "Идентификатор банковского счёта")
    @NotNull
    private Long accountId;

    @Schema(description = "Идентификатор профиля")
    @NotNull
    private Long profileId;
}