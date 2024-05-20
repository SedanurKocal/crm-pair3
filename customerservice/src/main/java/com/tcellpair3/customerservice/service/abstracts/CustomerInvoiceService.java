package com.tcellpair3.customerservice.service.abstracts;

import com.tcellpair3.customerservice.core.dtos.requests.contactmedium.CreateContactMediumRequest;
import com.tcellpair3.customerservice.core.dtos.requests.contactmedium.UpdateContactMediumRequest;
import com.tcellpair3.customerservice.core.dtos.requests.customerinvoice.CreateCustomerInvoiceRequest;
import com.tcellpair3.customerservice.core.dtos.requests.customerinvoice.UpdateCustomerInvoiceRequest;
import com.tcellpair3.customerservice.core.dtos.responses.contactmedium.CreateContactMediumResponse;
import com.tcellpair3.customerservice.core.dtos.responses.contactmedium.GetAllContactMediumResponse;
import com.tcellpair3.customerservice.core.dtos.responses.contactmedium.GetByIdContactMediumResponse;
import com.tcellpair3.customerservice.core.dtos.responses.contactmedium.UpdateContactMediumResponse;
import com.tcellpair3.customerservice.core.dtos.responses.customerinvoice.*;
import com.tcellpair3.customerservice.entities.CustomerInvoice;

import java.util.List;
import java.util.Optional;

public interface CustomerInvoiceService {
    CreateCustomerInvoiceResponse createCustomerInvoice(CreateCustomerInvoiceRequest request);
    UpdateCustomerInvoiceResponse updateCustomerInvoice(int id , UpdateCustomerInvoiceRequest request);
    void deleteCustomerInvoice(int id);
    List<GetAllCustomerInvoiceResponse> getAllCustomerInvoice();
    Optional<GetByIdCustomerInvoiceResponse> getByCustomerInvoiceId(int id);

    List<CustomerInvoiceWithCustomer> findByCustomerId(Integer customerId);
}
