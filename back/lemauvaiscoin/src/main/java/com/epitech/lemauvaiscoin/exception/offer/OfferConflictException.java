package com.epitech.lemauvaiscoin.exception.offer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Offre déjà existant")
public class OfferConflictException extends Exception{
}
