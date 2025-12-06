package com.bank.profile.mapper;

import com.bank.profile.dto.request.PassportRequestDto;
import com.bank.profile.dto.response.PassportResponseDto;
import com.bank.profile.entity.Passport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Маппер для {@link Passport}.
 */
@Mapper(componentModel = "spring")
public interface PassportMapper {

    @Mapping(target = "registration", ignore = true)
    @Mapping(target = "profile", ignore = true)
    Passport toEntity(PassportRequestDto requestDto);

    @Mapping(target = "registrationId", source = "registration.id")
    PassportResponseDto toResponseDto(Passport passport);

    @Mapping(target = "registration", ignore = true)
    @Mapping(target = "profile", ignore = true)
    void update(@MappingTarget Passport entity, PassportRequestDto requestDto);
}