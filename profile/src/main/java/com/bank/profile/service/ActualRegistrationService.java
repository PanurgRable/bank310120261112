package com.bank.profile.service;

import com.bank.profile.dto.request.ActualRegistrationRequestDto;
import com.bank.profile.dto.response.ActualRegistrationResponseDto;

/**
 * Сервис для работы с фактической регистрацией.
 */
public interface ActualRegistrationService {

    ActualRegistrationResponseDto create(ActualRegistrationRequestDto requestDto);

    ActualRegistrationResponseDto getById(Long id);

    ActualRegistrationResponseDto update(Long id, ActualRegistrationRequestDto requestDto);

    void delete(Long id);
}