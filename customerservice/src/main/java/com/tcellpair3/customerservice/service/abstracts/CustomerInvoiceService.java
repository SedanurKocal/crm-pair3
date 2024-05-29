package com.tcellpair3.customerservice.service.abstracts;

import com.tcellpair3.customerservice.core.dtos.requests.customerinvoice.CreateCustomerInvoiceRequest;
import com.tcellpair3.customerservice.core.dtos.requests.customerinvoice.UpdateCustomerInvoiceRequest;
import com.tcellpair3.customerservice.core.dtos.responses.customer.CustomerWithAddressesResponse;
import com.tcellpair3.customerservice.core.dtos.responses.customerinvoice.*;
import com.tcellpair3.customerservice.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerInvoiceService {
    CreateCustomerInvoiceResponse createCustomerInvoice(CreateCustomerInvoiceRequest request);
    UpdateCustomerInvoiceResponse updateCustomerInvoice(int id , UpdateCustomerInvoiceRequest request);
    void deleteCustomerInvoice(int id);
    List<GetAllCustomerInvoiceResponse> getAllCustomerInvoice();
    Optional<GetByIdCustomerInvoiceResponse> getByCustomerInvoiceId(int id);

    List<CustomerInvoiceWithCustomerResponse> findByCustomerId(Integer customerId);
    Optional<GetByIdCustomerInvoiceResponse> findByIdCustomerInvoice(Integer customerInvoiceId);
}
