package com.tcellpair3.customerservice.core.service.Concrete;

import com.tcellpair3.customerservice.core.exception.type.BusinessException;
import com.tcellpair3.customerservice.core.service.Abstract.CustomerValidationService;
import com.tcellpair3.customerservice.service.concretes.CustomerServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;

@Service
public class CustomerValidationServiceImpl implements CustomerValidationService {

    public void validateBirthdate(LocalDate birthdate) {
        int currentYear = Year.now().getValue();
        int birthYear = birthdate.getYear();
        if (birthYear > currentYear) {
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