package com.bank.profile.controller;

import com.bank.profile.dto.request.AuditRequestDto;
import com.bank.profile.dto.response.AuditResponseDto;
import com.bank.profile.service.AuditService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST-контроллер для аудита.
 */
@RestController
@RequestMapping("/api/v1/audit")
@RequiredArgsConstructor
@Validated
@Tag(name = "Audit", description = "CRUD операций для записей аудита")
public class AuditController {

    private final AuditService auditService;

    @Operation(summary = "Создать запись аудита")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuditResponseDto create(@Valid @RequestBody AuditRequestDto requestDto) {
        return auditService.create(requestDto);
    }

    @Operation(summary = "Получить запись аудита по id")
    @GetMapping("/{id}")
    public AuditResponseDto getById(@PathVariable Long id) {
        return auditService.getById(id);
    }

    @Operation(summary = "Обновить запись аудита")
    @PutMapping("/{id}")
    public AuditResponseDto update(@PathVariable Long id, @Valid @RequestBody AuditRequestDto requestDto) {
        return auditService.update(id, requestDto);
    }

    @Operation(summary = "Удалить запись аудита")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        auditService.delete(id);
    }
}