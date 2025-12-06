package com.bank.profile.controller;

import com.bank.profile.dto.request.RegistrationRequestDto;
import com.bank.profile.dto.response.RegistrationResponseDto;
import com.bank.profile.service.RegistrationService;
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
 * REST-контроллер для регистрации.
 */
@RestController
@RequestMapping("/api/v1/registrations")
@RequiredArgsConstructor
@Validated
@Tag(name = "Registration", description = "CRUD операций для постоянной регистрации")
public class RegistrationController {

    private final RegistrationService registrationService;

    @Operation(summary = "Создать запись регистрации")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RegistrationResponseDto create(@Valid @RequestBody RegistrationRequestDto requestDto) {
        return registrationService.create(requestDto);
    }

    @Operation(summary = "Получить запись регистрации по id")
    @GetMapping("/{id}")
    public RegistrationResponseDto getById(@PathVariable Long id) {
        return registrationService.getById(id);
    }

    @Operation(summary = "Обновить запись регистрации")
    @PutMapping("/{id}")
    public RegistrationResponseDto update(@PathVariable Long id,
                                          @Valid @RequestBody RegistrationRequestDto requestDto) {
        return registrationService.update(id, requestDto);
    }

    @Operation(summary = "Удалить запись регистрации")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        registrationService.delete(id);
    }
}