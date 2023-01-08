package com.epitech.lemauvaiscoin.exception.company;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Company déjà existant")
public class CompanyConflictException extends Exception{
}
