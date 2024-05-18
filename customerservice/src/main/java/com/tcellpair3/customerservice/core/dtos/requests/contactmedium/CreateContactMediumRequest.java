package com.tcellpair3.customerservice.core.dtos.requests.contactmedium;

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
public class CreateContactMediumRequest {
    @Email
    @NotBlank(message = "This field is required")
    private String email;

    @NotBlank
    private String mobilePhone;

    @Size(max = 10,min = 10,message = "Ev telefonu formatında başında 0 olmayacak şekilde 10 haneli veri girişi yapınız")
    private String homePhone;

    @Size(max = 12,min = 12,message = "Fax numarası formatında 12 haneli veri girişi yapınız")
    private String faxNumber;

    private int customerId;
}
