package com.tcellpair3.customerservice.core.exception.type;

public class DateTimeParseException extends java.time.format.DateTimeParseException {

    public DateTimeParseException(String message, CharSequence parsedData, int errorIndex) {
        super(message, parsedData, errorIndex);
    }

    public DateTimeParseException(String message, CharSequence parsedData, int errorIndex, Throwable cause) {
        super(message, parsedData, errorIndex, cause);
    }
}
