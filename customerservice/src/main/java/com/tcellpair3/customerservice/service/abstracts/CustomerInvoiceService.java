package com.tcellpair3.customerservice.service.abstracts;

import com.tcellpair3.customerservice.core.dtos.requests.customerinvoice.CreateCustomerInvoiceRequest;
import com.tcellpair3.customerservice.core.dtos.requests.customerinvoice.UpdateCustomerInvoiceRequest;
import com.tcellpair3.customerservice.core.dtos.responses.customer.CustomerWithCustomerInvoiceResponse;
import com.tcellpair3.customerservice.core.dtos.responses.customerinvoice.*;
import com.tcellpair3.customerservice.entities.CustomerInvoice;

import java.util.List;
import java.util.Optional;

public interface CustomerInvoiceService {
    CreateCustomerInvoiceResponse createCustomerInvoice(CreateCustomerInvoiceRequest request);
    UpdateCustomerInvoiceResponse updateCustomerInvoice(int id , UpdateCustomerInvoiceRequest request);
    void deleteCustomerInvoice(int id);
    List<CustomerInvoice> getAllCustomerInvoice();
    Optional<GetCustomerInvoiceByIdResponse> getByCustomerInvoiceId(int id);
    boolean existsById(Integer customerInvoiceId);
    List<CustomerInvoiceWithCustomerResponse> findByCustomerId(Integer customerId);
    Optional<GetCustomerInvoiceByIdResponse> findByIdCustomerInvoice(Integer customerInvoiceId);
    CustomerWithCustomerInvoiceResponse getCustomerByOrderId(Integer invoiceId);
}
