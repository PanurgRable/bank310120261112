package com.bank.profile.service.impl;

import com.bank.profile.dto.request.AccountDetailsIdRequestDto;
import com.bank.profile.dto.response.AccountDetailsIdResponseDto;
import com.bank.profile.entity.AccountDetailsId;
import com.bank.profile.entity.Profile;
import com.bank.profile.exception.EntityNotFoundException;
import com.bank.profile.mapper.AccountDetailsIdMapper;
import com.bank.profile.repository.AccountDetailsIdRepository;
import com.bank.profile.repository.ProfileRepository;
import com.bank.profile.service.AccountDetailsIdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Реализация сервиса связок профиля и счёта.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AccountDetailsIdServiceImpl implements AccountDetailsIdService {

    private final AccountDetailsIdRepository accountDetailsIdRepository;
    private final ProfileRepository profileRepository;
    private final AccountDetailsIdMapper accountDetailsIdMapper;

    @Override
    @Transactional
    public AccountDetailsIdResponseDto create(AccountDetailsIdRequestDto requestDto) {
        Profile profile = findProfile(requestDto.getProfileId());
        AccountDetailsId entity = accountDetailsIdMapper.toEntity(requestDto);
        entity.setProfile(profile);
        AccountDetailsId saved = accountDetailsIdRepository.save(entity);
        return accountDetailsIdMapper.toResponseDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public AccountDetailsIdResponseDto getById(Long id) {
        AccountDetailsId detailsId = findByIdOrThrow(id);
        return accountDetailsIdMapper.toResponseDto(detailsId);
    }

    @Override
    @Transactional
    public AccountDetailsIdResponseDto update(Long id, AccountDetailsIdRequestDto requestDto) {
        AccountDetailsId detailsId = findByIdOrThrow(id);
        Profile profile = findProfile(requestDto.getProfileId());
        accountDetailsIdMapper.update(detailsId, requestDto);
        detailsId.setProfile(profile);
        AccountDetailsId saved = accountDetailsIdRepository.save(detailsId);
        return accountDetailsIdMapper.toResponseDto(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        AccountDetailsId detailsId = findByIdOrThrow(id);
        accountDetailsIdRepository.delete(detailsId);
    }

    private AccountDetailsId findByIdOrThrow(Long id) {
        return accountDetailsIdRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Связка профиля и счёта с id {} не найдена", id);
                    return new EntityNotFoundException("Связка профиля и счёта не найдена");
                });
    }

    private Profile findProfile(Long id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Профиль с id {} не найден", id);
                    return new EntityNotFoundException("Профиль не найден");
                });
    }
}