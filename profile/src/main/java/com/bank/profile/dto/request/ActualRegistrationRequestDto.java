package com.bank.profile.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO для создания или обновления фактического адреса регистрации.
 */
@Data
public class ActualRegistrationRequestDto {

    @Schema(description = "Страна", example = "Россия")
    @NotBlank
    @Size(max = 40)
    private String country;

    @Schema(description = "Регион")
    @Size(max = 160)
    private String region;

    @Schema(description = "Город")
    @Size(max = 160)
    private String city;

    @Schema(description = "Район")
    @Size(max = 160)
    private String district;

    @Schema(description = "Населенный пункт")
    @Size(max = 230)
    private String locality;

    @Schema(description = "Улица")
    @Size(max = 230)
    private String street;

    @Schema(description = "Номер дома")
    @Size(max = 20)
    private String houseNumber;

    @Schema(description = "Корпус")
    @Size(max = 20)
    private String houseBlock;

    @Schema(description = "Квартира")
    @Size(max = 40)
    private String flatNumber;

    @Schema(description = "Почтовый индекс", example = "101000")
    @NotNull
    private Long postalIndex;
}