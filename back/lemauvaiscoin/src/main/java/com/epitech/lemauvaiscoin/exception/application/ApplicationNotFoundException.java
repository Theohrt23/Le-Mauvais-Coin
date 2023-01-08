package com.epitech.lemauvaiscoin.exception.application;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Postulation non trouvée")
public class ApplicationNotFoundException extends Exception{
}
