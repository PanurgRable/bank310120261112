package com.bank.profile.mapper;

import com.bank.profile.dto.request.RegistrationRequestDto;
import com.bank.profile.dto.response.RegistrationResponseDto;
import com.bank.profile.entity.Registration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Маппер для {@link Registration}.
 */
@Mapper(componentModel = "spring")
public interface RegistrationMapper {

    Registration toEntity(RegistrationRequestDto requestDto);

    RegistrationResponseDto toResponseDto(Registration registration);

    void update(@MappingTarget Registration entity, RegistrationRequestDto requestDto);
}