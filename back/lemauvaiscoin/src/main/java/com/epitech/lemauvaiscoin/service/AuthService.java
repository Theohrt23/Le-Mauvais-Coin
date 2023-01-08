package com.epitech.lemauvaiscoin.service;

import com.epitech.lemauvaiscoin.exception.user.UserNotFoundException;
import com.epitech.lemauvaiscoin.mapper.dto.LoginDTO;
import com.epitech.lemauvaiscoin.mapper.dto.PasswordDTO;
import com.epitech.lemauvaiscoin.response.JwtResponse;

public interface AuthService {
    JwtResponse authenticateUser(LoginDTO loginDTO);

    boolean isGoodPassword(PasswordDTO passwordDTO) throws UserNotFoundException;
}
