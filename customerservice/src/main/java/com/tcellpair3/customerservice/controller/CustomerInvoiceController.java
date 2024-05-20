package com.tcellpair3.customerservice.controller;

import com.tcellpair3.customerservice.core.dtos.requests.customerinvoice.CreateCustomerInvoiceRequest;
import com.tcellpair3.customerservice.core.dtos.responses.customerinvoice.CreateCustomerInvoiceResponse;
import com.tcellpair3.customerservice.core.dtos.responses.customerinvoice.CustomerInvoiceWithAddressResponse;
import com.tcellpair3.customerservice.core.dtos.responses.customerinvoice.CustomerInvoiceWithCustomerResponse;
import com.tcellpair3.customerservice.service.abstracts.CustomerInvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customerInvoices/")
@RequiredArgsConstructor
public class CustomerInvoiceController {
    private final CustomerInvoiceService customerInvoiceService;

    @GetMapping("/customer/{id}")
    public List<CustomerInvoiceWithCustomerResponse> findByCustomer(Integer customerId)
    {
        return customerInvoiceService.findByCustomerId(customerId);
    }
    @GetMapping("/{invoiceId}/addresses")
    public ResponseEntity<CustomerInvoiceWithAddressResponse> getCustomerInvoiceWithAddresses(@PathVariable Integer invoiceId) {
        CustomerInvoiceWithAddressResponse customerInvoiceDTO = customerInvoiceService.findCustomerInvoiceWithAddressesById(invoiceId);
        if (customerInvoiceDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerInvoiceDTO, HttpStatus.OK);
    }
    @PostMapping
    public CreateCustomerInvoiceResponse createCustomerInvoice(@Valid @RequestBody CreateCustomerInvoiceRequest request){
        return customerInvoiceService.createCustomerInvoice(request);
    }

}
