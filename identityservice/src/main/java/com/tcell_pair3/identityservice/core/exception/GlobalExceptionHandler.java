package com.tcell_pair3.identityservice.core.exception;

import com.tcell_pair3.identityservice.core.exception.details.BusinessExceptionDetails;
import com.tcell_pair3.identityservice.core.exception.details.UnauthorizedExceptionDetails;
import com.tcell_pair3.identityservice.core.exception.type.BusinessException;
import com.tcell_pair3.identityservice.core.exception.type.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public UnauthorizedExceptionDetails handleRuntimeException(UnauthorizedException exception){
        UnauthorizedExceptionDetails unAuthorizedExceptionDetails = new UnauthorizedExceptionDetails();
        unAuthorizedExceptionDetails.setDetail(exception.getMessage());
        return unAuthorizedExceptionDetails;
    }
    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BusinessExceptionDetails handleRuntimeException(BusinessException exception)
    {
        BusinessExceptionDetails businessExceptionDetails = new BusinessExceptionDetails();
        businessExceptionDetails.setTitle(exception.getMessage());
        return businessExceptionDetails;
    }
}