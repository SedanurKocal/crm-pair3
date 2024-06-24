package com.tcellpair3.customerservice.core.dtos.requests.customerinvoice;

import com.tcellpair3.customerservice.entities.AccountStatus;
import com.tcellpair3.customerservice.entities.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerInvoiceRequest {

    private AccountStatus accountStatus;

    private AccountType accountType;

    private int customerId;
}
