package com.epitech.lemauvaiscoin.exception.offer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Offre non trouvé")
public class OfferNotFoundException extends Exception{
}
