package com.tcellpair3.customerservice.core.service.Concrete;

import com.tcellpair3.customerservice.core.exception.type.BusinessException;
import com.tcellpair3.customerservice.core.service.Abstract.ContactMediumServiceValidation;
import org.springframework.stereotype.Service;

@Service
public class ContactMediumValidationServiceImpl implements ContactMediumServiceValidation {
    @Override
    public void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.length() != 11 ) {
            throw new BusinessException("GSM number must be 11 characters long");
        }

        try {
            long gsmNumber = Long.parseLong(phoneNumber);
            if (gsmNumber <= 0) {
                throw new BusinessException("GSM number must be a positive integer");
            }
        } catch (NumberFormatException e) {
            throw new BusinessException("GSM number must be a valid integer");
        }
    }
}
