package com.tcellpair3.customerservice.core.dtos.responses.contactmedium;

import com.tcellpair3.customerservice.entities.Customer;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateContactMediumResponse {

    private int id;
    private String email;
    private String mobilePhone;
    private String homePhone;
    private String faxNumber;
    private int customerId;
}
