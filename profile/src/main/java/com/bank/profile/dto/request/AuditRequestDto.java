package com.bank.profile.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import lombok.Data;

/**
 * DTO для записи аудита.
 */
@Data
public class AuditRequestDto {

    @Schema(description = "Тип сущности", example = "Profile")
    @NotBlank
    private String entityType;

    @Schema(description = "Тип операции", example = "CREATE")
    @NotBlank
    private String operationType;

    @Schema(description = "Кто создал запись")
    @NotBlank
    private String createdBy;

    @Schema(description = "Кто изменил запись")
    private String modifiedBy;

    @Schema(description = "Время создания")
    @NotNull
    private OffsetDateTime createdAt;

    @Schema(description = "Время изменения")
    private OffsetDateTime modifiedAt;

    @Schema(description = "JSON после изменения")
    private String newEntityJson;

    @Schema(description = "JSON текущего состояния")
    @NotBlank
    private String entityJson;
}