package com.bank.profile.controller;

import com.bank.profile.dto.request.AccountDetailsIdRequestDto;
import com.bank.profile.dto.response.AccountDetailsIdResponseDto;
import com.bank.profile.service.AccountDetailsIdService;
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
 * REST-контроллер для связок профиля и счёта.
 */
@RestController
@RequestMapping("/api/v1/account-details")
@RequiredArgsConstructor
@Validated
@Tag(name = "Account Details", description = "CRUD операций для связки профиля и банковского счёта")
public class AccountDetailsIdController {

    private final AccountDetailsIdService accountDetailsIdService;

    @Operation(summary = "Создать связку профиля и счёта")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDetailsIdResponseDto create(@Valid @RequestBody AccountDetailsIdRequestDto requestDto) {
        return accountDetailsIdService.create(requestDto);
    }

    @Operation(summary = "Получить связку по id")
    @GetMapping("/{id}")
    public AccountDetailsIdResponseDto getById(@PathVariable Long id) {
        return accountDetailsIdService.getById(id);
    }

    @Operation(summary = "Обновить связку профиля и счёта")
    @PutMapping("/{id}")
    public AccountDetailsIdResponseDto update(@PathVariable Long id,
                                              @Valid @RequestBody AccountDetailsIdRequestDto requestDto) {
        return accountDetailsIdService.update(id, requestDto);
    }

    @Operation(summary = "Удалить связку профиля и счёта")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        accountDetailsIdService.delete(id);
    }
}