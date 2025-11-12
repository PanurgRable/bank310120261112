package com.bank.authorization.aspect;

import com.bank.authorization.dto.AuditDto;
import com.bank.authorization.entity.User;
import com.bank.authorization.service.AuditService;
import com.bank.authorization.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import static com.bank.authorization.constants.OperationEnum.CREATE;
import static com.bank.authorization.constants.OperationEnum.UPDATE;
import static com.bank.authorization.constants.EntityEnum.USER;

@Aspect
@Component
@PersistenceContext
@AllArgsConstructor
public class UserAuditAspect {

    private final AuditService auditService;
    private final UserService userService;
    private EntityManager entityManager;

    @AfterReturning(
            pointcut = "execution(* com.bank.authorization.service.UserService.createUser(..))",
            returning = "newUser")
    public void auditUserCreation(User newUser) {
        AuditDto auditDto = auditService.buildAuditRecord(USER, CREATE, null, newUser);
        auditService.createAudit(auditDto);
    }

    @Around("execution(* com.bank.authorization.service.UserService.updateUser(..)) && args(userId, ..)")
    public Object auditUserUpdate(ProceedingJoinPoint pjp, Long userId) throws Throwable {

        User oldUser = userService.getUserById(userId);
        entityManager.detach(oldUser);

        Object result = pjp.proceed();

        User updatedUser = (User) result;

        AuditDto auditDto = auditService.buildAuditRecord(USER, UPDATE, oldUser, updatedUser);
        auditService.createAudit(auditDto);

        return result;
    }
}
