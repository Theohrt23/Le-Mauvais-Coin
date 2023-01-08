package com.epitech.lemauvaiscoin.response;

public class JwtResponse {
    private String jwttoken;

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }
}
