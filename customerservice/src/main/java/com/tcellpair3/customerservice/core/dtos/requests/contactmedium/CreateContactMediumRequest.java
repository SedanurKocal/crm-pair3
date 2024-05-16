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
    @Size(max = 11,min = 11,message = "Telefon numarası formatında başında 0 olacak şekilde 11 haneli veri girişi yapınız")
    private String mobilePhone;

    @Size(max = 11,min = 11,message = "Ev telefonu formatında başında 0 olacak şekilde 11 haneli veri girişi yapınız")
    private String homePhone;

    @Size(max = 12,min = 12,message = "Fax numarası formatında 12 haneli veri girişi yapınız")
    private String faxNumber;

    private int customerId;
}
