package com.tcellpair3.customerservice.core.dtos.responses.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerWithCustomerInvoiceResponse {
    private int id;
    private int customerInvoiceId;
    private int customerId;
    private String firstName;
    private String lastName;

}