package com.dario.useraccounts.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AttributeException extends RuntimeException {

    public AttributeException(String message){
        super(message);
    }
}
