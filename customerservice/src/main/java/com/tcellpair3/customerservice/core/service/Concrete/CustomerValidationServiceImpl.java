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


}