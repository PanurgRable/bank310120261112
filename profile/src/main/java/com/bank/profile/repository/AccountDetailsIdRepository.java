package com.bank.profile.repository;

import com.bank.profile.entity.AccountDetailsId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для связок профиля и счетов.
 */
public interface AccountDetailsIdRepository extends JpaRepository<AccountDetailsId, Long> {
}