package com.epitech.lemauvaiscoin.service.impl;

import com.epitech.lemauvaiscoin.domain.User;
import com.epitech.lemauvaiscoin.domain.UserRole;
import com.epitech.lemauvaiscoin.exception.user.UserConflictException;
import com.epitech.lemauvaiscoin.exception.user.UserNotFoundException;
import com.epitech.lemauvaiscoin.repository.UserRepository;
import com.epitech.lemauvaiscoin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) throws UserConflictException {
        if (userRepository.existsByUsername(user.getUsername()) || userRepository.existsByMail(user.getMail())) {
            throw new UserConflictException();
        }

        user.setJoinDate(LocalDate.now().toString());

        userRepository.save(user);
        return user;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) throws UserNotFoundException {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException();
        }

        return userRepository.getById(userId);
    }

    @Override
    public User updateUserById(User user) throws UserNotFoundException {
        if (user.getId() == null || !userRepository.existsById(user.getId())) {
            throw new UserNotFoundException();
        }

        user.setJoinDate(userRepository.getById(user.getId()).getJoinDate());

        userRepository.save(user);

        return user;
    }

    @Override
    public void deleteUserById(Long userId) throws UserNotFoundException {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException();
        }

        userRepository.deleteById(userId);
    }

    @Override
    public boolean isCompany(Long userId) throws UserNotFoundException {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException();
        }

        if (userRepository.getById(userId).getRole().equals(UserRole.Values.COMPANY)){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean isAdmin(Long userId) throws UserNotFoundException {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException();
        }

        if (userRepository.getById(userId).getRole().equals(UserRole.Values.ADMIN)){
            return true;
        }else {
            return false;
        }
    }
}
