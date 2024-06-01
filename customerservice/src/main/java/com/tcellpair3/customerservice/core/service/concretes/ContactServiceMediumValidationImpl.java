package com.tcellpair3.customerservice.core.service.concretes;

import com.tcellpair3.customerservice.core.exception.type.BusinessException;
import com.tcellpair3.customerservice.core.service.abstracts.ContactMediumValidationService;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceMediumValidationImpl implements ContactMediumValidationService {
    @Override
    public void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.length() != 10 ) {
            throw new BusinessException("GSM number must be 10 characters long");
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
