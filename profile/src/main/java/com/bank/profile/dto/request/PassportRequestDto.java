package com.bank.profile.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Data;

/**
 * DTO для паспорта.
 */
@Data
public class PassportRequestDto {

    @Schema(description = "Серия паспорта", example = "1111")
    @NotNull
    private Integer series;

    @Schema(description = "Номер паспорта", example = "123456")
    @NotNull
    private Long number;

    @Schema(description = "Фамилия", example = "Иванов")
    @NotBlank
    @Size(max = 255)
    private String lastName;

    @Schema(description = "Имя", example = "Иван")
    @NotBlank
    @Size(max = 255)
    private String firstName;

    @Schema(description = "Отчество")
    @Size(max = 255)
    private String middleName;

    @Schema(description = "Пол", example = "МУЖ")
    @NotBlank
    @Size(max = 3)
    @Pattern(regexp = "МУЖ|ЖЕН", message = "Пол должен быть МУЖ или ЖЕН")
    private String gender;

    @Schema(description = "Дата рождения")
    @NotNull
    private LocalDate birthDate;

    @Schema(description = "Место рождения")
    @NotBlank
    @Size(max = 480)
    private String birthPlace;

    @Schema(description = "Кем выдан")
    @NotBlank
    private String issuedBy;

    @Schema(description = "Дата выдачи")
    @NotNull
    private LocalDate dateOfIssue;

    @Schema(description = "Код подразделения", example = "123456")
    @NotNull
    private Integer divisionCode;

    @Schema(description = "Дата окончания")
    private LocalDate expirationDate;

    @Schema(description = "Идентификатор регистрации")
    @NotNull
    private Long registrationId;
}