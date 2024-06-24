package com.tcellpair3.customerservice.core.service.concretes;

import com.tcellpair3.customerservice.core.service.abstracts.ContactMediumValidationService;
import org.springframework.stereotype.Service;
import org.turkcell.tcell.exception.exceptions.type.BaseBusinessException;

import java.util.regex.Pattern;

@Service
public class ContactServiceMediumValidationImpl implements ContactMediumValidationService {

    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("^0[0-9]{10}$");

    @Override
    public void validatePhoneNumber(String phoneNumber) {
        if (!PHONE_NUMBER_PATTERN.matcher(phoneNumber).matches()) {
            throw new BaseBusinessException("Telefon numarası 0 ile başlamalı ve 11 haneli olmalıdır");
        }

        try {
            long gsmNumber = Long.parseLong(phoneNumber);
            if (gsmNumber <= 0) {
                throw new BaseBusinessException("GSM numarası pozitif bir tamsayı olmalıdır");
            }
        } catch (NumberFormatException e) {
            throw new BaseBusinessException("GSM numarası geçerli bir tamsayı olmalıdır");
        }
    }
}
