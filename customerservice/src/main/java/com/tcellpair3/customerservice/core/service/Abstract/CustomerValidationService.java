package com.tcellpair3.customerservice.core.service.Abstract;

import java.time.LocalDate;

public interface CustomerValidationService {
    public void validateBirthdate(LocalDate birthdate);
    public boolean isValidTC(String nationalId);

}
