package com.bank.profile.service;

import com.bank.profile.dto.request.AccountDetailsIdRequestDto;
import com.bank.profile.dto.response.AccountDetailsIdResponseDto;

/**
 * Сервис связок профиля и счета.
 */
public interface AccountDetailsIdService {

    AccountDetailsIdResponseDto create(AccountDetailsIdRequestDto requestDto);

    AccountDetailsIdResponseDto getById(Long id);

    AccountDetailsIdResponseDto update(Long id, AccountDetailsIdRequestDto requestDto);

    void delete(Long id);
}