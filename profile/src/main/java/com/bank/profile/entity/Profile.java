package com.bank.profile.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Банковский профиль клиента.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profile")
public class Profile {

    /**
     * Технический идентификатор профиля, заполняется базой автоинкрементом
     * (IDENTITY) из Liquibase-скрипта.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "phone_number", nullable = false)
    private Long phoneNumber;

    @Column(length = 264)
    private String email;

    @Column(name = "name_on_card", length = 370)
    private String nameOnCard;

    @Column(unique = true)
    private Long inn;

    @Column(unique = true)
    private Long snils;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "passport_id", nullable = false)
    private Passport passport;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actual_registration_id")
    private ActualRegistration actualRegistration;

    @OneToMany(mappedBy = "profile")
    private Set<AccountDetailsId> accountDetails;
}