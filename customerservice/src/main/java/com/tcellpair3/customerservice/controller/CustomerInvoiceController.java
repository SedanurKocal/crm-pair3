package com.tcellpair3.customerservice.controller;

import com.tcellpair3.customerservice.core.dtos.requests.customer.CreateCustomerRequest;
import com.tcellpair3.customerservice.core.dtos.requests.customerinvoice.CreateCustomerInvoiceRequest;
import com.tcellpair3.customerservice.core.dtos.responses.customer.CreateCustomerResponse;
import com.tcellpair3.customerservice.core.dtos.responses.customerinvoice.CreateCustomerInvoiceResponse;
import com.tcellpair3.customerservice.core.dtos.responses.customerinvoice.CustomerInvoiceWithCustomer;
import com.tcellpair3.customerservice.entities.CustomerInvoice;
import com.tcellpair3.customerservice.service.abstracts.CustomerInvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customerInvoices/")
@RequiredArgsConstructor
public class CustomerInvoiceController {
    private final CustomerInvoiceService customerInvoiceService;

    @GetMapping("/customer/{id}")
    public List<CustomerInvoiceWithCustomer> findByCustomer(Integer customerId)
    {
        return customerInvoiceService.findByCustomerId(customerId);
    }
    @PostMapping
    public CreateCustomerInvoiceResponse createCustomerInvoice(@Valid @RequestBody CreateCustomerInvoiceRequest request){
        return customerInvoiceService.createCustomerInvoice(request);
    }

}
