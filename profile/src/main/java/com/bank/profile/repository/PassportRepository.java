package com.bank.profile.repository;

import com.bank.profile.entity.Passport;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для {@link Passport}.
 */
public interface PassportRepository extends JpaRepository<Passport, Long> {
}