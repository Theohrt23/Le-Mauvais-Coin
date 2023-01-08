package com.epitech.lemauvaiscoin.exception.admin;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Admin déjà existant")
public class AdminConflictException extends Exception {
}
