package com.tcellpair3.cartservice.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerWithCustomerInvoiceResponse {
    private Integer customerId;
    private Integer customerInvoiceId;
}