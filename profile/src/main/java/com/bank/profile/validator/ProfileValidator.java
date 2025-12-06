package com.bank.profile.validator;

import com.bank.profile.dto.request.ProfileRequestDto;
import com.bank.profile.exception.ValidationException;
import com.bank.profile.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Валидация профиля.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ProfileValidator {

    private final ProfileRepository profileRepository;

    /**
     * Проверка уникальности ИНН и СНИЛС.
     *
     * @param requestDto запрос профиля
     * @param id идентификатор текущего профиля (null при создании)
     */
    public void validateUniqueness(ProfileRequestDto requestDto, Long id) {
        if (requestDto.getInn() != null) {
            boolean exists = id == null
                    ? profileRepository.existsByInn(requestDto.getInn())
                    : profileRepository.existsByInnAndIdNot(requestDto.getInn(), id);
            if (exists) {
                log.info("Профиль с ИНН {} уже существует", requestDto.getInn());
                throw new ValidationException("Профиль с указанным ИНН уже существует");
            }
        }

        if (requestDto.getSnils() != null) {
            boolean exists = id == null
                    ? profileRepository.existsBySnils(requestDto.getSnils())
                    : profileRepository.existsBySnilsAndIdNot(requestDto.getSnils(), id);
            if (exists) {
                log.info("Профиль со СНИЛС {} уже существует", requestDto.getSnils());
                throw new ValidationException("Профиль с указанным СНИЛС уже существует");
            }
        }
    }
}