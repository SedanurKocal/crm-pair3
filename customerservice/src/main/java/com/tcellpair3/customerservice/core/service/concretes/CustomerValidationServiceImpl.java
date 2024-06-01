package com.tcellpair3.customerservice.core.service.concretes;

import com.tcellpair3.customerservice.core.exception.type.BusinessException;
import com.tcellpair3.customerservice.core.service.abstracts.CustomerValidationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CustomerValidationServiceImpl implements CustomerValidationService {

    public void validateBirthdate(LocalDate birthdate) {

        LocalDate today = LocalDate.now();

        // Birthdate should not be in the future
        if (birthdate.isAfter(today)) {
            throw new BusinessException("Birthdate cannot be in the future");
        }


    }

    @Override
    public boolean isValidTC(String nationalId) {
        if (nationalId == null || nationalId.length() != 11) {
            throw new BusinessException("TC Kimlik numarası 11 karakter olmalıdır.");
        }

        try {
            Long.parseLong(nationalId);
        } catch (NumberFormatException e) {
            throw new BusinessException("TC Kimlik numarası sadece rakamlardan oluşmalıdır.");
        }

        int lastDigit = Character.getNumericValue(nationalId.charAt(10));
        if (lastDigit % 2 != 0) {
            throw new BusinessException("TC Kimlik numarasının son rakamı çift olmalıdır.");
        }


        return true;
    }


}