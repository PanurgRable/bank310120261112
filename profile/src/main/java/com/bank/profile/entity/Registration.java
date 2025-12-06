package com.bank.profile.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Адрес постоянной регистрации клиента.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "registration")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, length = 166)
    private String country;

    @Column(length = 160)
    private String region;

    @Column(length = 160)
    private String city;

    @Column(length = 160)
    private String district;

    @Column(length = 230)
    private String locality;

    @Column(length = 230)
    private String street;

    @Column(name = "house_number", length = 20)
    private String houseNumber;

    @Column(name = "house_block", length = 20)
    private String houseBlock;

    @Column(name = "flat_number", length = 40)
    private String flatNumber;

    @Column(name = "index", nullable = false)
    private Long postalIndex;

    @Column(name = "Column")
    private Integer columnNumber;
}
