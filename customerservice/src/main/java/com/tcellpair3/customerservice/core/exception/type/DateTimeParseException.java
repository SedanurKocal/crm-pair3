package com.tcellpair3.customerservice.core.exception.type;

import com.tcellpair3.customerservice.core.exception.details.DateTimeParseExceptionDetails;

public class DateTimeParseException extends java.time.format.DateTimeParseException {


    public DateTimeParseException(String message, CharSequence parsedData, int errorIndex) {
        super(message, parsedData, errorIndex);
    }
}
