package com.bank.profile.service.impl;

import com.bank.profile.dto.request.AuditRequestDto;
import com.bank.profile.dto.response.AuditResponseDto;
import com.bank.profile.entity.Audit;
import com.bank.profile.exception.EntityNotFoundException;
import com.bank.profile.mapper.AuditMapper;
import com.bank.profile.repository.AuditRepository;
import com.bank.profile.service.AuditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Реализация сервиса аудита.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {

    private final AuditRepository auditRepository;
    private final AuditMapper auditMapper;

    @Override
    @Transactional
    public AuditResponseDto create(AuditRequestDto requestDto) {
        Audit audit = auditMapper.toEntity(requestDto);
        Audit saved = auditRepository.save(audit);
        return auditMapper.toResponseDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public AuditResponseDto getById(Long id) {
        Audit audit = findByIdOrThrow(id);
        return auditMapper.toResponseDto(audit);
    }

    @Override
    @Transactional
    public AuditResponseDto update(Long id, AuditRequestDto requestDto) {
        Audit audit = findByIdOrThrow(id);
        auditMapper.update(audit, requestDto);
        Audit saved = auditRepository.save(audit);
        return auditMapper.toResponseDto(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Audit audit = findByIdOrThrow(id);
        auditRepository.delete(audit);
    }

    private Audit findByIdOrThrow(Long id) {
        return auditRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Запись аудита с id {} не найдена", id);
                    return new EntityNotFoundException("Запись аудита не найдена");
                });
    }
}