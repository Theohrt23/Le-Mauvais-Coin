package com.epitech.lemauvaiscoin.service;

import com.epitech.lemauvaiscoin.domain.User;
import com.epitech.lemauvaiscoin.exception.user.UserConflictException;
import com.epitech.lemauvaiscoin.exception.user.UserNotFoundException;

import java.util.List;

public interface UserService {
    User createUser(User user) throws UserConflictException;

    List<User> getAllUser();

    User getUserById(Long userId) throws UserNotFoundException;

    User updateUserById(User user) throws UserNotFoundException;

    void deleteUserById(Long userId) throws UserNotFoundException;

    boolean isCompany(Long userId) throws UserNotFoundException;

    boolean isAdmin(Long userId) throws UserNotFoundException;
}
