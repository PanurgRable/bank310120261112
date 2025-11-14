package com.bank.authorization.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class InitUsersConfig {
    @Value("${app.init-users.admin.username}")
    private Long adminUsername;

    @Value("${app.init-users.admin.password}")
    private String adminPassword;

    @Value("${app.init-users.user.username}")
    private Long userUsername;

    @Value("${app.init-users.user.password}")
    private String userPassword;
}
