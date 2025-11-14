package com.bank.authorization.service;

import com.bank.authorization.constants.EntityEnum;
import com.bank.authorization.constants.OperationEnum;
import com.bank.authorization.dto.AuditDto;
import com.bank.authorization.entity.Audit;
import com.bank.authorization.entity.User;
import com.bank.authorization.mapper.AuditMapper;
import com.bank.authorization.repository.AuditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@AllArgsConstructor
public class AuditServiceImpl implements AuditService {

    private final AuditRepository auditRepository;
    private final AuditMapper auditMapper;

    private static final String EMPTY_ENTITY_PLACEHOLDER = "null";

    @Override
    public void createAudit(AuditDto auditDto) {
        log.info("Creating new audit record: {}", auditDto);

        try {
            Audit audit = auditMapper.toEntity(auditDto);
            Audit savedAudit = auditRepository.save(audit);
            log.info("Audit record successfully created with ID: {}", savedAudit.getId());
            auditMapper.toDto(savedAudit);
        } catch (Exception e) {
            log.error("Error creating audit record: {}", auditDto, e);
            throw e;
        }
    }

    @Override
    public AuditDto buildAuditRecord(EntityEnum entityType,
                                     OperationEnum operationType,
                                     User oldUser,
                                     User newUser) {
        log.info("Building audit record. Entity type: {}, Operation: {}",
                entityType, operationType);

        AuditDto auditDto = new AuditDto();
        auditDto.setEntityType(entityType);
        auditDto.setOperationType(operationType);
        auditDto.setCreatedBy(getCurrentUsername());
        auditDto.setModifiedBy(getCurrentUsername());
        auditDto.setCreatedAt(LocalDateTime.now());
        auditDto.setModifiedAt(LocalDateTime.now());

        auditDto.setEntityJson(oldUser != null ? oldUser.toString() : EMPTY_ENTITY_PLACEHOLDER);
        auditDto.setNewEntityJson(newUser != null ? newUser.toString() : EMPTY_ENTITY_PLACEHOLDER);

        log.info("Built audit record: {} for {} operation on entity {}",
                auditDto, operationType, entityType);

        return auditDto;
    }

    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken) {
            throw new SecurityException("User must be authenticated to perform audit operations");
        }
        return authentication.getName();
    }
}
