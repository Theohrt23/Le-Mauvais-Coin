package com.epitech.lemauvaiscoin.service.impl;

import com.epitech.lemauvaiscoin.exception.user.UserNotFoundException;
import com.epitech.lemauvaiscoin.mapper.dto.LoginDTO;
import com.epitech.lemauvaiscoin.mapper.dto.PasswordDTO;
import com.epitech.lemauvaiscoin.response.JwtResponse;
import com.epitech.lemauvaiscoin.service.AuthService;
import com.epitech.lemauvaiscoin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtServiceImpl jwtService;

    private final UserService userService;

    private final PasswordEncoder encoder;

    public String encryptPwd(String password) {
        return encoder.encode(password);
    }

    @Override
    public JwtResponse authenticateUser(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        JwtResponse jwt = new JwtResponse(jwtService.generateJwtToken(authentication));
        return jwt;
    }


    @Override
    public  boolean isGoodPassword(PasswordDTO passwordDTO) throws UserNotFoundException {
        return encoder.matches(passwordDTO.getPassword(), userService.getUserById(passwordDTO.getUser_id()).getPassword());
    }

}
