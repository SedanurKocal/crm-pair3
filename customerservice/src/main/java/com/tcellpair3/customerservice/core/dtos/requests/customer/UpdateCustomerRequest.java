package com.tcellpair3.customerservice.core.dtos.requests.customer;

import com.tcellpair3.customerservice.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class UpdateCustomerRequest {

    @NotNull(message = "This field is required")
    private int accountNumber;

    @NotBlank(message = "This field is required")
    @Size(max = 50,message = "Maksimum 50 karakterlik veri girişi yapınız.")
    private String firstName;

    @NotBlank(message = "This field is required")
    @Size(max = 50,message = "Maksimum 50 karakterlik veri girişi yapınız.")
    private String lastName;

    @Size(max = 50,message = "Maksimum 50 karakterlik veri girişi yapınız.")
    private String middleName;

    @NotBlank(message = "This field is required")
    private String nationalId;

    private String motherName;

    private String fatherName;

    private LocalDate birthdate;

    private Gender gender;

}
