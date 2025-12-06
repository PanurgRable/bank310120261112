package com.bank.profile.service;

import com.bank.profile.dto.request.ProfileRequestDto;
import com.bank.profile.dto.response.ProfileResponseDto;

/**
 * Сервис профилей.
 */
public interface ProfileService {

    ProfileResponseDto create(ProfileRequestDto requestDto);

    ProfileResponseDto getById(Long id);

    ProfileResponseDto update(Long id, ProfileRequestDto requestDto);

    void delete(Long id);
}