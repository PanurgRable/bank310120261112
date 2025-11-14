package com.bank.authorization.mapper;

import com.bank.authorization.dto.AuditDto;
import com.bank.authorization.entity.Audit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuditMapper {

    AuditDto toDto(Audit audit);

    Audit toEntity(AuditDto auditDto);
}
