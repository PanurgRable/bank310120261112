package com.bank.authorization.init;

import com.bank.authorization.constants.RoleEnum;
import com.bank.authorization.entity.User;
import com.bank.authorization.repository.UserRepository;
import com.bank.authorization.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        if (!userRepository.existsByProfileId(123L)) {
            User admin = new User();
            admin.setProfileId(123L);
            admin.setPassword("admin");
            admin.setRole(RoleEnum.ADMIN);

            userService.createUser(admin);
            log.info("Created initial admin user with profileId: 123");
        }

        if (!userRepository.existsByProfileId(124L)) {
            User user = new User();
            user.setProfileId(124L);
            user.setPassword("user");
            user.setRole(RoleEnum.USER);

            userService.createUser(user);
            log.info("Created initial user with profileId: 124");
        }
    }
}
