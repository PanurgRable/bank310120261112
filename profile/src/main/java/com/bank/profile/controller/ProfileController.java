package com.bank.profile.controller;

import com.bank.profile.dto.request.ProfileRequestDto;
import com.bank.profile.dto.response.ProfileResponseDto;
import com.bank.profile.service.ProfileService;
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
 * REST-контроллер для профилей.
 */
@RestController
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
@Validated
@Tag(name = "Profile", description = "CRUD операций для банковского профиля")
public class ProfileController {

    private final ProfileService profileService;

    @Operation(summary = "Создать профиль")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProfileResponseDto create(@Valid @RequestBody ProfileRequestDto requestDto) {
        return profileService.create(requestDto);
    }

    @Operation(summary = "Получить профиль по id")
    @GetMapping("/{id}")
    public ProfileResponseDto getById(@PathVariable Long id) {
        return profileService.getById(id);
    }

    @Operation(summary = "Обновить профиль")
    @PutMapping("/{id}")
    public ProfileResponseDto update(@PathVariable Long id, @Valid @RequestBody ProfileRequestDto requestDto) {
        return profileService.update(id, requestDto);
    }

    @Operation(summary = "Удалить профиль")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        profileService.delete(id);
    }
}