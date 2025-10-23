package com.bank.authorization.service;

import com.bank.authorization.constants.OperationEnum;
import com.bank.authorization.dto.AuditDto;
import com.bank.authorization.entity.User;

public interface AuditService {

    void createAudit(AuditDto auditDto);

    AuditDto buildAuditRecord(String entityType,
                              OperationEnum operationType,
                              User oldUser,
                              User newUser
    );
}
