package com.bank.profile.repository;

import com.bank.profile.entity.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для аудита изменений.
 */
public interface AuditRepository extends JpaRepository<Audit, Long> {
}