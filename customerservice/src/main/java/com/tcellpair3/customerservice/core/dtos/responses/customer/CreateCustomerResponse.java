package com.tcellpair3.customerservice.core.dtos.responses.customer;

import com.tcellpair3.customerservice.entities.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerResponse {

    private int id;

    private String firstName;


    private String lastName;


    private String middleName;


    private String nationalId;


    private String motherName;


    private String fatherName;


    private LocalDate birthdate;

    private Gender gender;
}
