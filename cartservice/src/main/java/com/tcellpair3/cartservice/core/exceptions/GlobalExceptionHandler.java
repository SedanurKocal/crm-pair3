package com.tcellpair3.cartservice.core.exceptions;

import com.tcellpair3.cartservice.core.exceptions.details.BusinessExceptionDetails;
import com.tcellpair3.cartservice.core.exceptions.type.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BusinessExceptionDetails handleRuntimeException(BusinessException exception)
    {
        BusinessExceptionDetails businessExceptionDetails = new BusinessExceptionDetails();
        businessExceptionDetails.setTitle(exception.getMessage());
        return businessExceptionDetails;
    }

}