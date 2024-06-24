package com.tcellpair3.customerservice.core.dtos.responses.customerinvoice;

import com.tcellpair3.customerservice.entities.CustomerInvoice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchResultsCustomerInvoiceResponse {

    private int customerId;

    private String firstName;

    private String lastName;

    private List<CustomerInvoice> customerInvoices;
}
