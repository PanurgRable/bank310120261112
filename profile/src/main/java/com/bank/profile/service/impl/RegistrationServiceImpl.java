package com.bank.profile.service.impl;

import com.bank.profile.dto.request.RegistrationRequestDto;
import com.bank.profile.dto.response.RegistrationResponseDto;
import com.bank.profile.entity.Registration;
import com.bank.profile.exception.EntityNotFoundException;
import com.bank.profile.mapper.RegistrationMapper;
import com.bank.profile.repository.RegistrationRepository;
import com.bank.profile.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Реализация сервиса регистрации.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final RegistrationMapper registrationMapper;

    @Override
    @Transactional
    public RegistrationResponseDto create(RegistrationRequestDto requestDto) {
        Registration entity = registrationMapper.toEntity(requestDto);
        Registration saved = registrationRepository.save(entity);
        return registrationMapper.toResponseDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public RegistrationResponseDto getById(Long id) {
        Registration registration = findByIdOrThrow(id);
        return registrationMapper.toResponseDto(registration);
    }

    @Override
    @Transactional
    public RegistrationResponseDto update(Long id, RegistrationRequestDto requestDto) {
        Registration registration = findByIdOrThrow(id);
        registrationMapper.update(registration, requestDto);
        Registration saved = registrationRepository.save(registration);
        return registrationMapper.toResponseDto(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Registration registration = findByIdOrThrow(id);
        registrationRepository.delete(registration);
    }

    private Registration findByIdOrThrow(Long id) {
        return registrationRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Регистрация с id {} не найдена", id);
                    return new EntityNotFoundException("Регистрация не найдена");
                });
    }
}