package com.bank.profile.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * DTO ответа с фактическим адресом регистрации.
 */
@Data
public class ActualRegistrationResponseDto {

    @Schema(description = "Идентификатор записи")
    private Long id;

    private String country;
    private String region;
    private String city;
    private String district;
    private String locality;
    private String street;
    private String houseNumber;
    private String houseBlock;
    private String flatNumber;
    private Long postalIndex;
}