package com.bank.authorization.service;

import com.bank.authorization.constants.RoleEnum;
import com.bank.authorization.entity.User;
import com.bank.authorization.exeptionhandler.EntityNotFoundException;

public interface UserService {

    User getUserById(long id) throws EntityNotFoundException;

    User getUserByProfileId(long profileId) throws EntityNotFoundException;

    User createUser(User user);

    User updateUser(long id, long profileId, RoleEnum role, String password) throws EntityNotFoundException;

    void deleteUser(long id) throws EntityNotFoundException;

    boolean hasAdminRole(String token);
}
