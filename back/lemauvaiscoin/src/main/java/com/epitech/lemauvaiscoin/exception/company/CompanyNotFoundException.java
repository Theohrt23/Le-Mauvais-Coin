package com.epitech.lemauvaiscoin.exception.company;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Company non trouvé")
public class CompanyNotFoundException extends Exception{
}
