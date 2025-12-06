package com.bank.profile.service.impl;

import com.bank.profile.dto.request.ProfileRequestDto;
import com.bank.profile.dto.response.ProfileResponseDto;
import com.bank.profile.entity.ActualRegistration;
import com.bank.profile.entity.Passport;
import com.bank.profile.entity.Profile;
import com.bank.profile.exception.EntityNotFoundException;
import com.bank.profile.mapper.ProfileMapper;
import com.bank.profile.repository.ActualRegistrationRepository;
import com.bank.profile.repository.PassportRepository;
import com.bank.profile.repository.ProfileRepository;
import com.bank.profile.service.ProfileService;
import com.bank.profile.validator.ProfileValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Реализация сервиса профилей.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final PassportRepository passportRepository;
    private final ActualRegistrationRepository actualRegistrationRepository;
    private final ProfileMapper profileMapper;
    private final ProfileValidator profileValidator;

    @Override
    @Transactional
    public ProfileResponseDto create(ProfileRequestDto requestDto) {
        profileValidator.validateUniqueness(requestDto, null);
        Passport passport = findPassport(requestDto.getPassportId());
        ActualRegistration actualRegistration = findActualRegistration(requestDto.getActualRegistrationId());

        Profile profile = profileMapper.toEntity(requestDto);
        profile.setPassport(passport);
        profile.setActualRegistration(actualRegistration);

        Profile saved = profileRepository.save(profile);
        return profileMapper.toResponseDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ProfileResponseDto getById(Long id) {
        Profile profile = findByIdOrThrow(id);
        return profileMapper.toResponseDto(profile);
    }

    @Override
    @Transactional
    public ProfileResponseDto update(Long id, ProfileRequestDto requestDto) {
        Profile profile = findByIdOrThrow(id);
        profileValidator.validateUniqueness(requestDto, id);

        Passport passport = findPassport(requestDto.getPassportId());
        ActualRegistration actualRegistration = findActualRegistration(requestDto.getActualRegistrationId());

        profileMapper.update(profile, requestDto);
        profile.setPassport(passport);
        profile.setActualRegistration(actualRegistration);

        Profile saved = profileRepository.save(profile);
        return profileMapper.toResponseDto(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Profile profile = findByIdOrThrow(id);
        profileRepository.delete(profile);
    }

    private Profile findByIdOrThrow(Long id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Профиль с id {} не найден", id);
                    return new EntityNotFoundException("Профиль не найден");
                });
    }

    private Passport findPassport(Long id) {
        return passportRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Паспорт с id {} не найден", id);
                    return new EntityNotFoundException("Паспорт не найден");
                });
    }

    private ActualRegistration findActualRegistration(Long id) {
        if (id == null) {
            return null;
        }
        return actualRegistrationRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Фактическая регистрация с id {} не найдена", id);
                    return new EntityNotFoundException("Фактическая регистрация не найдена");
                });
    }
}