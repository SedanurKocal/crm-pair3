package com.tcellpair3.customerservice.core.dtos.responses.contactmedium;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdContactMediumResponse {
    private int id;
    private String email;
    private String mobilePhone;
    private String homePhone;
    private String faxNumber;
    private int customerId;
}
