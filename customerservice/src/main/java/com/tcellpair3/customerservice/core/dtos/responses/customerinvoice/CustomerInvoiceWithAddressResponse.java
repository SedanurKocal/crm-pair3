package com.tcellpair3.customerservice.core.dtos.responses.customerinvoice;

import com.tcellpair3.customerservice.entities.AccountStatus;
import com.tcellpair3.customerservice.entities.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInvoiceWithAddressResponse {

    private Integer id;

    private int accountName;

    private AccountStatus accountStatus;

    private AccountType accountType;

    private Integer customerId;
}
