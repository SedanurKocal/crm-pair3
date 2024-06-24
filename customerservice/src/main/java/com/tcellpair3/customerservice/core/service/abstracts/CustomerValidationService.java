package com.tcellpair3.customerservice.core.service.abstracts;

import java.time.LocalDate;

public interface CustomerValidationService {

    public void validateBirthdate(LocalDate birthdate);

    public boolean isValidTC(String nationalId);
}
