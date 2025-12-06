package com.bank.profile.repository;

import com.bank.profile.entity.ActualRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для {@link ActualRegistration}.
 */
public interface ActualRegistrationRepository extends JpaRepository<ActualRegistration, Long> {
}