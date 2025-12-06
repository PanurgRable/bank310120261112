package com.bank.profile.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import lombok.Data;

/**
 * DTO ответа для аудита.
 */
@Data
public class AuditResponseDto {

    @Schema(description = "Идентификатор записи")
    private Long id;

    private String entityType;
    private String operationType;
    private String createdBy;
    private String modifiedBy;
    private OffsetDateTime createdAt;
    private OffsetDateTime modifiedAt;
    private String newEntityJson;
    private String entityJson;
}