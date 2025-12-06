package com.bank.profile.controller;

import com.bank.profile.dto.request.PassportRequestDto;
import com.bank.profile.dto.response.PassportResponseDto;
import com.bank.profile.service.PassportService;
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
 * REST-контроллер для паспортов.
 */
@RestController
@RequestMapping("/api/v1/passports")
@RequiredArgsConstructor
@Validated
@Tag(name = "Passport", description = "CRUD операций для паспортов")
public class PassportController {

    private final PassportService passportService;

    @Operation(summary = "Создать паспорт")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PassportResponseDto create(@Valid @RequestBody PassportRequestDto requestDto) {
        return passportService.create(requestDto);
    }

    @Operation(summary = "Получить паспорт по id")
    @GetMapping("/{id}")
    public PassportResponseDto getById(@PathVariable Long id) {
        return passportService.getById(id);
    }

    @Operation(summary = "Обновить паспорт")
    @PutMapping("/{id}")
    public PassportResponseDto update(@PathVariable Long id, @Valid @RequestBody PassportRequestDto requestDto) {
        return passportService.update(id, requestDto);
    }

    @Operation(summary = "Удалить паспорт")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        passportService.delete(id);
    }
}