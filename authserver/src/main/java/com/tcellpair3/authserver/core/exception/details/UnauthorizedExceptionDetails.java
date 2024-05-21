package com.tcellpair3.authserver.core.exception.details;

public class UnauthorizedExceptionDetails extends ProblemDetails{
    public UnauthorizedExceptionDetails() {
        setTitle("Hatalı Giriş");
    }
}
