package com.dario.useraccounts.exceptions;

public class PermissionDeniedException  extends RuntimeException {

    public PermissionDeniedException(String message) {
        super(message);
    }
}
