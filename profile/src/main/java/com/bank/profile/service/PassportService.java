package com.bank.profile.service;

import com.bank.profile.dto.request.PassportRequestDto;
import com.bank.profile.dto.response.PassportResponseDto;

/**
 * Сервис паспортных данных.
 */
public interface PassportService {

    PassportResponseDto create(PassportRequestDto requestDto);

    PassportResponseDto getById(Long id);

    PassportResponseDto update(Long id, PassportRequestDto requestDto);

    void delete(Long id);
}