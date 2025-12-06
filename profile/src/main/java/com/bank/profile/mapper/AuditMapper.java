package com.bank.profile.mapper;

import com.bank.profile.dto.request.AuditRequestDto;
import com.bank.profile.dto.response.AuditResponseDto;
import com.bank.profile.entity.Audit;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Маппер для {@link Audit}.
 */
@Mapper(componentModel = "spring")
public interface AuditMapper {

    Audit toEntity(AuditRequestDto requestDto);

    AuditResponseDto toResponseDto(Audit audit);

    void update(@MappingTarget Audit entity, AuditRequestDto requestDto);
}