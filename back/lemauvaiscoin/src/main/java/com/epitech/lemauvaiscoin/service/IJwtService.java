package com.epitech.lemauvaiscoin.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.Authentication;

public interface IJwtService {

    Boolean validateJwtToken(String jwt);

    String getUserNameFromJwtToken(String token);

    String generateJwtToken(Authentication authentication);

    DecodedJWT decodeJwt(String jwt);

}
