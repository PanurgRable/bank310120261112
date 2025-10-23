package com.bank.authorization.service;

import com.bank.authorization.constants.RoleEnum;
import com.bank.authorization.entity.User;
import com.bank.authorization.exeptionhandler.EntityNotFoundException;
import com.bank.authorization.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    @Override
    public User getUserById(long id) throws EntityNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> {
            log.warn("User with id {} does not exist", id);
            return new EntityNotFoundException("User with id " + id + " not found");
        });
    }

    @Override
    public User getUserByProfileId(long profileId) throws EntityNotFoundException {
        return userRepository.findByProfileId(profileId)
                .orElseThrow(() -> {
                    log.warn("User with profileId {} does not exist", profileId);
                    return new EntityNotFoundException("User with profileId " + profileId + " not found");
                });
    }

    @Override
    @Transactional
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        log.info("User created successfully. ID: {}, Profile ID: {}, Role: {}",
                savedUser.getId(), savedUser.getProfileId(), savedUser.getRole());
        return savedUser;
    }

    @Override
    @Transactional
    public User updateUser(long id, long profileId, RoleEnum role, String password) throws EntityNotFoundException {
        User existingUser = getUserById(id);
        existingUser.setRole(role);
        existingUser.setProfileId(profileId);

        if (password != null && !password.isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(password));
        }

        User updatedUser = userRepository.save(existingUser);
        log.info("User updated successfully. ID: {}, New Role: {}, Password changed: {}",
                id, role, password != null && !password.isEmpty());
        return updatedUser;
    }

    @Override
    @Transactional
    public void deleteUser(long id) throws EntityNotFoundException {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            log.info("User deleted successfully. ID: {}", id);
        } else {
            log.warn("User with id {} does not exist for deletion", id);
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
    }

    @Override
    public boolean hasAdminRole(String token) {
        String role = jwtService.extractRoleFromToken(token);
        return "ADMIN".equals(role);
    }
}
