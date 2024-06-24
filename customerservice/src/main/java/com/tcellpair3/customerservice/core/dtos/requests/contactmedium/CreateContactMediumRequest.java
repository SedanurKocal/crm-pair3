package com.tcellpair3.customerservice.core.dtos.requests.contactmedium;

import com.tcellpair3.customerservice.entities.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

    @NotBlank(message = "This field is required")
    @Pattern(regexp = "^0[0-9]{10}$", message = "Cep telefonu formatında başında 0 olacak şekilde 11 haneli veri girişi yapınız")
    private String mobilePhone;

    @Pattern(regexp = "^$|^0[0-9]{10}$", message = "Ev telefonu formatında başında 0 olacak şekilde 11 haneli veri girişi yapınız veya boş bırakabilirsiniz")
    private String homePhone;

    @Pattern(regexp = "^$|^0[0-9]{11}$", message = "Fax numarası formatında başında 0 olacak şekilde 12 haneli veri girişi yapınız veya boş bırakabilirsiniz")
    private String faxNumber;

    private int customerId;
}
