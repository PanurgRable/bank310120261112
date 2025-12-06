package com.bank.profile.mapper;

import com.bank.profile.dto.request.ProfileRequestDto;
import com.bank.profile.dto.response.ProfileResponseDto;
import com.bank.profile.entity.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Маппер для {@link Profile}.
 */
@Mapper(componentModel = "spring")
public interface ProfileMapper {

    @Mapping(target = "passport", ignore = true)
    @Mapping(target = "actualRegistration", ignore = true)
    @Mapping(target = "accountDetails", ignore = true)
    Profile toEntity(ProfileRequestDto requestDto);

    @Mapping(target = "passportId", source = "passport.id")
    @Mapping(target = "actualRegistrationId", source = "actualRegistration.id")
    ProfileResponseDto toResponseDto(Profile profile);

    @Mapping(target = "passport", ignore = true)
    @Mapping(target = "actualRegistration", ignore = true)
    @Mapping(target = "accountDetails", ignore = true)
    void update(@MappingTarget Profile entity, ProfileRequestDto requestDto);
}