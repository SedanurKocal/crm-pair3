package com.tcellpair3.customerservice.controller;

import com.tcellpair3.customerservice.core.dtos.requests.customerinvoice.CreateCustomerInvoiceRequest;
import com.tcellpair3.customerservice.core.dtos.responses.customerinvoice.CreateCustomerInvoiceResponse;
import com.tcellpair3.customerservice.core.dtos.responses.customerinvoice.CustomerInvoiceWithCustomerResponse;
import com.tcellpair3.customerservice.core.dtos.responses.customerinvoice.GetCustomerInvoiceByIdResponse;
import com.tcellpair3.customerservice.service.abstracts.CustomerInvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/customerInvoices")
@RequiredArgsConstructor
public class CustomerInvoiceController {
    private final CustomerInvoiceService customerInvoiceService;

    @GetMapping("/customer/{id}")
    public List<CustomerInvoiceWithCustomerResponse> findByCustomer(Integer customerId)
    {
        return customerInvoiceService.findByCustomerId(customerId);
    }
    @GetMapping("/{customerInvoiceId}")
    public Optional<GetCustomerInvoiceByIdResponse> findByIdCustomerInvoice(@PathVariable Integer customerInvoiceId)
    {
        return customerInvoiceService.findByIdCustomerInvoice(customerInvoiceId);
    }
    @PostMapping
    public CreateCustomerInvoiceResponse createCustomerInvoice(@Valid @RequestBody CreateCustomerInvoiceRequest request){
        return customerInvoiceService.createCustomerInvoice(request);
    }

}
