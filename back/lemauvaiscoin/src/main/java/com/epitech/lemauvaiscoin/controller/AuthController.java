package com.epitech.lemauvaiscoin.controller;

import com.epitech.lemauvaiscoin.exception.user.UserNotFoundException;
import com.epitech.lemauvaiscoin.mapper.dto.LoginDTO;
import com.epitech.lemauvaiscoin.mapper.dto.PasswordDTO;
import com.epitech.lemauvaiscoin.response.JwtResponse;
import com.epitech.lemauvaiscoin.service.AuthService;
import com.epitech.lemauvaiscoin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signin")
    public JwtResponse authenticateUser(@Valid @RequestBody LoginDTO loginDTO) {
        return authService.authenticateUser(loginDTO);
    }

    @PostMapping("/verif")
    public boolean isgoodPassword (@Valid @RequestBody PasswordDTO passwordDTO) throws UserNotFoundException {
        return authService.isGoodPassword(passwordDTO);
    }
}
