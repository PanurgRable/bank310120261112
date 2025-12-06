package com.bank.profile.controller;

import com.bank.profile.dto.request.ActualRegistrationRequestDto;
import com.bank.profile.dto.response.ActualRegistrationResponseDto;
import com.bank.profile.service.ActualRegistrationService;
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
 * REST-контроллер для фактических адресов: экспонирует CRUD-эндпоинты,
 * валидирует входящие DTO через Bean Validation и передаёт работу слою сервисов.
 * Аннотации {@link Tag} и {@link Operation} описывают методы в Swagger UI,
 * чтобы новичку было проще понять назначение каждого запроса.
 */
@RestController
@RequestMapping("/api/v1/actual-registrations")
@RequiredArgsConstructor
@Validated
@Tag(name = "Actual Registration", description = "CRUD операций для фактического адреса")
public class ActualRegistrationController {

    private final ActualRegistrationService actualRegistrationService;

    @Operation(summary = "Создать запись фактической регистрации")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ActualRegistrationResponseDto create(@Valid @RequestBody ActualRegistrationRequestDto requestDto) {
        return actualRegistrationService.create(requestDto);
    }

    @Operation(summary = "Получить запись фактической регистрации по id")
    @GetMapping("/{id}")
    public ActualRegistrationResponseDto getById(@PathVariable Long id) {
        return actualRegistrationService.getById(id);
    }

    @Operation(summary = "Обновить запись фактической регистрации")
    @PutMapping("/{id}")
    public ActualRegistrationResponseDto update(@PathVariable Long id,
                                                @Valid @RequestBody ActualRegistrationRequestDto requestDto) {
        return actualRegistrationService.update(id, requestDto);
    }

    @Operation(summary = "Удалить запись фактической регистрации")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        actualRegistrationService.delete(id);
    }
}