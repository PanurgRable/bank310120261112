package com.bank.profile.service.impl;

import com.bank.profile.dto.request.ActualRegistrationRequestDto;
import com.bank.profile.dto.response.ActualRegistrationResponseDto;
import com.bank.profile.entity.ActualRegistration;
import com.bank.profile.exception.EntityNotFoundException;
import com.bank.profile.mapper.ActualRegistrationMapper;
import com.bank.profile.repository.ActualRegistrationRepository;
import com.bank.profile.service.ActualRegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Реализация сервиса фактической регистрации.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ActualRegistrationServiceImpl implements ActualRegistrationService {

    private final ActualRegistrationRepository actualRegistrationRepository;
    private final ActualRegistrationMapper actualRegistrationMapper;

    @Override
    @Transactional
    public ActualRegistrationResponseDto create(ActualRegistrationRequestDto requestDto) {
        ActualRegistration entity = actualRegistrationMapper.toEntity(requestDto);
        ActualRegistration saved = actualRegistrationRepository.save(entity);
        return actualRegistrationMapper.toResponseDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ActualRegistrationResponseDto getById(Long id) {
        ActualRegistration registration = findByIdOrThrow(id);
        return actualRegistrationMapper.toResponseDto(registration);
    }

    @Override
    @Transactional
    public ActualRegistrationResponseDto update(Long id, ActualRegistrationRequestDto requestDto) {
        ActualRegistration registration = findByIdOrThrow(id);
        actualRegistrationMapper.update(registration, requestDto);
        ActualRegistration saved = actualRegistrationRepository.save(registration);
        return actualRegistrationMapper.toResponseDto(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        ActualRegistration registration = findByIdOrThrow(id);
        actualRegistrationRepository.delete(registration);
    }

    private ActualRegistration findByIdOrThrow(Long id) {
        return actualRegistrationRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Фактическая регистрация с id {} не найдена", id);
                    return new EntityNotFoundException("Фактическая регистрация не найдена");
                });
    }
}