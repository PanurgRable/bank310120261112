package com.bank.profile.service.impl;

import com.bank.profile.dto.request.PassportRequestDto;
import com.bank.profile.dto.response.PassportResponseDto;
import com.bank.profile.entity.Passport;
import com.bank.profile.entity.Registration;
import com.bank.profile.exception.EntityNotFoundException;
import com.bank.profile.mapper.PassportMapper;
import com.bank.profile.repository.PassportRepository;
import com.bank.profile.repository.RegistrationRepository;
import com.bank.profile.service.PassportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Реализация сервиса паспортов.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PassportServiceImpl implements PassportService {

    private final PassportRepository passportRepository;
    private final RegistrationRepository registrationRepository;
    private final PassportMapper passportMapper;

    @Override
    @Transactional
    public PassportResponseDto create(PassportRequestDto requestDto) {
        Registration registration = findRegistration(requestDto.getRegistrationId());
        Passport passport = passportMapper.toEntity(requestDto);
        passport.setRegistration(registration);
        Passport saved = passportRepository.save(passport);
        return passportMapper.toResponseDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public PassportResponseDto getById(Long id) {
        Passport passport = findByIdOrThrow(id);
        return passportMapper.toResponseDto(passport);
    }

    @Override
    @Transactional
    public PassportResponseDto update(Long id, PassportRequestDto requestDto) {
        Passport passport = findByIdOrThrow(id);
        Registration registration = findRegistration(requestDto.getRegistrationId());
        passportMapper.update(passport, requestDto);
        passport.setRegistration(registration);
        Passport saved = passportRepository.save(passport);
        return passportMapper.toResponseDto(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Passport passport = findByIdOrThrow(id);
        passportRepository.delete(passport);
    }

    private Passport findByIdOrThrow(Long id) {
        return passportRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Паспорт с id {} не найден", id);
                    return new EntityNotFoundException("Паспорт не найден");
                });
    }

    private Registration findRegistration(Long id) {
        return registrationRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Регистрация с id {} не найдена", id);
                    return new EntityNotFoundException("Регистрация не найдена");
                });
    }
}