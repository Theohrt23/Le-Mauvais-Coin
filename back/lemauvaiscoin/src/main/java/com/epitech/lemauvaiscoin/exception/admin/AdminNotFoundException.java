package com.epitech.lemauvaiscoin.exception.admin;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Admin non trouvé")
public class AdminNotFoundException extends Exception{
}
