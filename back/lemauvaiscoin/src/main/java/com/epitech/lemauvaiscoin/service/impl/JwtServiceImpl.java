package com.epitech.lemauvaiscoin.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.epitech.lemauvaiscoin.security.SecurityConstants;
import com.epitech.lemauvaiscoin.security.SecurityUserDetails;
import com.epitech.lemauvaiscoin.service.IJwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtServiceImpl implements IJwtService {

    @Value("${lemauvaiscoin.jwt.secret}")
    private String jwtSecret;

    @Value("${lemauvaiscoin.jwt.jwtExpireMinutes}")
    private Long jwtExpireMinutes;

    private Algorithm jwtAlgorithm;

    @PostConstruct()
    public void init() {
        this.jwtAlgorithm = Algorithm.HMAC512(jwtSecret);
    }


    @Override
    public String generateJwtToken(Authentication authentication) {
        SecurityUserDetails userPrincipal = (SecurityUserDetails) authentication.getPrincipal();
        return JWT
                .create()
                .withSubject(userPrincipal.getUsername())
                .withArrayClaim(SecurityConstants.JWT_ROLES_NAMESPACE, userPrincipal.getRoles().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new))
                .withClaim(SecurityConstants.TOKEN_ID, userPrincipal.getId())
                .withClaim(SecurityConstants.TOKEN_MAIL, userPrincipal.getMail())
                .withExpiresAt(Date.from(LocalDateTime.now().plus(Duration.ofMinutes(jwtExpireMinutes)).atZone(ZoneId.systemDefault()).toInstant()))
                .sign(Algorithm.HMAC512(jwtSecret));
    }

    @Override
    public String getUserNameFromJwtToken(String token) {
        return decodeJwt(token).getSubject();
    }

    @Override
    public Boolean validateJwtToken(String jwt) {
        JWTVerifier verifier = JWT.require(jwtAlgorithm).build();
        try {
            verifier.verify(jwt);
        } catch (JWTVerificationException e) {
            return false;
        }
        return true;
    }

    @Override
    public DecodedJWT decodeJwt(String jwt) {
        return JWT.decode(jwt);
    }
}
