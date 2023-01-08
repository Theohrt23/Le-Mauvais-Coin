package com.epitech.lemauvaiscoin.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "User déjà existant")
public class UserConflictException extends Exception{
}
