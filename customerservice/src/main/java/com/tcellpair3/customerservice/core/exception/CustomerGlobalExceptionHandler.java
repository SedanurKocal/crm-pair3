package com.tcellpair3.customerservice.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.turkcell.tcell.exception.exceptions.details.BusinessExceptionDetails;
import org.turkcell.tcell.exception.exceptions.details.DateTimeParseExceptionDetails;
import org.turkcell.tcell.exception.exceptions.details.IllegalArgumentExceptionDetails;
import org.turkcell.tcell.exception.exceptions.type.BaseBusinessException;

import java.time.format.DateTimeParseException;

@RestControllerAdvice
public class CustomerGlobalExceptionHandler {

    @ExceptionHandler({BaseBusinessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BusinessExceptionDetails handleRuntimeException(BaseBusinessException exception)
    {
        BusinessExceptionDetails businessExceptionDetails = new BusinessExceptionDetails();
        businessExceptionDetails.setTitle(exception.getMessage());
        return businessExceptionDetails;
    }
    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public IllegalArgumentExceptionDetails handleIllegalArgumentException(IllegalArgumentException exception){
        IllegalArgumentExceptionDetails illegalArgumentExceptionDetails = new IllegalArgumentExceptionDetails();
        illegalArgumentExceptionDetails.setTitle(exception.getMessage());
        return illegalArgumentExceptionDetails;
    }
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DateTimeParseExceptionDetails handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        DateTimeParseExceptionDetails details = new DateTimeParseExceptionDetails();
        if (ex.getCause() instanceof DateTimeParseException) {
            DateTimeParseException dtpe = (DateTimeParseException) ex.getCause();
            details.setTitle(dtpe.getMessage());
        } else {
            details.setTitle(ex.getMessage());
        }
        return details;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }


}