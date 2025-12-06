package com.bank.profile.service;

import com.bank.profile.dto.request.RegistrationRequestDto;
import com.bank.profile.dto.response.RegistrationResponseDto;

/**
 * Сервис регистрации.
 */
public interface RegistrationService {

    RegistrationResponseDto create(RegistrationRequestDto requestDto);

    RegistrationResponseDto getById(Long id);

    RegistrationResponseDto update(Long id, RegistrationRequestDto requestDto);

    void delete(Long id);
}