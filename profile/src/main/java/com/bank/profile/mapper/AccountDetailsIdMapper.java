package com.bank.profile.mapper;

import com.bank.profile.dto.request.AccountDetailsIdRequestDto;
import com.bank.profile.dto.response.AccountDetailsIdResponseDto;
import com.bank.profile.entity.AccountDetailsId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Маппер для {@link AccountDetailsId}.
 */
@Mapper(componentModel = "spring")
public interface AccountDetailsIdMapper {

    @Mapping(target = "profile", ignore = true)
    AccountDetailsId toEntity(AccountDetailsIdRequestDto requestDto);

    @Mapping(target = "profileId", source = "profile.id")
    AccountDetailsIdResponseDto toResponseDto(AccountDetailsId accountDetailsId);

    @Mapping(target = "profile", ignore = true)
    void update(@MappingTarget AccountDetailsId entity, AccountDetailsIdRequestDto requestDto);
}