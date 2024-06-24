package com.tcell_pair3.identityservice.core.exception.type;

import org.springframework.security.authentication.BadCredentialsException;

public class UnauthorizedException extends BadCredentialsException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
