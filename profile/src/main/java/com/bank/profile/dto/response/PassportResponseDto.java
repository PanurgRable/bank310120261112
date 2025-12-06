package com.bank.profile.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.Data;

/**
 * DTO ответа по паспорту.
 */
@Data
public class PassportResponseDto {

    @Schema(description = "Идентификатор паспорта")
    private Long id;

    private Integer series;
    private Long number;
    private String lastName;
    private String firstName;
    private String middleName;
    private String gender;
    private LocalDate birthDate;
    private String birthPlace;
    private String issuedBy;
    private LocalDate dateOfIssue;
    private Integer divisionCode;
    private LocalDate expirationDate;
    private Long registrationId;
}