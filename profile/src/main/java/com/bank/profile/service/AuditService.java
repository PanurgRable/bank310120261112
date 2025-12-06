package com.bank.profile.service;

import com.bank.profile.dto.request.AuditRequestDto;
import com.bank.profile.dto.response.AuditResponseDto;

/**
 * Сервис аудита изменений.
 */
public interface AuditService {

    AuditResponseDto create(AuditRequestDto requestDto);

    AuditResponseDto getById(Long id);

    AuditResponseDto update(Long id, AuditRequestDto requestDto);

    void delete(Long id);
}