package com.tcell_pair3.identityservice.core.service.concretes;

import com.tcell_pair3.identityservice.core.service.abstracts.ValidateEmailService;
import org.springframework.stereotype.Service;
import org.turkcell.tcell.exception.exceptions.type.BaseBusinessException;

import java.util.regex.Pattern;

@Service
public class ValidateEmailServiceImpl implements ValidateEmailService {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    @Override
    public void validateEmail(String email) {
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new BaseBusinessException("Please enter a valid e-mail address'");
        }
    }
}
