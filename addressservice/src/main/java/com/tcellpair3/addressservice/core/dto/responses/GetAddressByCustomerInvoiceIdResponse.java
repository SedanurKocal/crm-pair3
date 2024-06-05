package com.tcellpair3.addressservice.core.dto.responses;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAddressByCustomerInvoiceIdResponse {

    private Integer id;
    private Integer customerInvoiceId;
    private String firstName;
    private String lastName;
}
