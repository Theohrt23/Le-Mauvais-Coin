package com.epitech.lemauvaiscoin.controller;

import com.epitech.lemauvaiscoin.domain.UserRole;
import com.epitech.lemauvaiscoin.exception.user.UserConflictException;
import com.epitech.lemauvaiscoin.exception.user.UserNotFoundException;
import com.epitech.lemauvaiscoin.mapper.UserMapper;
import com.epitech.lemauvaiscoin.mapper.dto.UserDTO;
import com.epitech.lemauvaiscoin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping("/{userId}")
    @RolesAllowed({UserRole.Values.USER,UserRole.Values.ADMIN})
    public UserDTO getUserById(@PathVariable(value = "userId") Long userId) throws UserNotFoundException {
        return  userMapper.userToUserDto(userService.getUserById(userId));
    }

    @GetMapping()
    @RolesAllowed({UserRole.Values.ADMIN})
    public List<UserDTO> getAllUser() {
        return userMapper.userListToUserListDto(userService.getAllUser());
    }

    @PostMapping()
    public UserDTO createUser(@Valid @RequestBody UserDTO userDTO) throws UserConflictException {
        return userMapper.userToUserDto(userService.createUser(userMapper.userDtoToUser(userDTO)));
    }

    @PutMapping()
    @RolesAllowed({UserRole.Values.ADMIN, UserRole.Values.USER})
    public UserDTO updateUserById(@Valid @RequestBody UserDTO userDTO) throws UserNotFoundException {
        return userMapper.userToUserDto(userService.updateUserById(userMapper.userDtoToUser(userDTO)));
    }

    @DeleteMapping("/{userId}")
    @RolesAllowed({UserRole.Values.ADMIN})
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable(value = "userId") Long userId) throws UserNotFoundException {
        userService.deleteUserById(userId);
    }

    @GetMapping("/isAdmin/{userId}")
    @RolesAllowed({UserRole.Values.ADMIN})
    public boolean isAdmin(@PathVariable(value = "userId") Long userId) throws UserNotFoundException {
       return userService.isAdmin(userId);
    }

    @GetMapping("/isCompany/{userId}")
    @RolesAllowed({UserRole.Values.ADMIN})
    public boolean isCompany(@PathVariable(value = "userId") Long userId) throws UserNotFoundException {
        return userService.isCompany(userId);
    }

}
