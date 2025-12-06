package com.bank.profile.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * DTO ответа для связки профиля и счёта.
 */
@Data
public class AccountDetailsIdResponseDto {

    @Schema(description = "Идентификатор связки")
    private Long id;

    private Long accountId;
    private Long profileId;
}