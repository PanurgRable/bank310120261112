package com.bank.profile.mapper;

import com.bank.profile.dto.request.ActualRegistrationRequestDto;
import com.bank.profile.dto.response.ActualRegistrationResponseDto;
import com.bank.profile.entity.ActualRegistration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Маппер для {@link ActualRegistration}.
 */
@Mapper(componentModel = "spring")
public interface ActualRegistrationMapper {

    ActualRegistration toEntity(ActualRegistrationRequestDto requestDto);

    ActualRegistrationResponseDto toResponseDto(ActualRegistration actualRegistration);

    void update(@MappingTarget ActualRegistration entity, ActualRegistrationRequestDto requestDto);
}