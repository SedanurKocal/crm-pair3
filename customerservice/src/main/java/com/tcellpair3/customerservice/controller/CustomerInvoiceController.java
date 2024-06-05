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
//TODO: GET CUSTOMER BY ORDER NUMBER FR-3
//    @GetMapping("/{invoiceId}/customer")
//    public ResponseEntity<CustomerWithCustomerInvoiceResponse> getCustomerByInvoiceId(@PathVariable Integer invoiceId) {
//        CustomerWithCustomerInvoiceResponse response = customerInvoiceService.getCustomerByInvoiceId(invoiceId);
//        if (response != null) {
//            return ResponseEntity.ok(response);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
    @PostMapping
    public CreateCustomerInvoiceResponse createCustomerInvoice(@Valid @RequestBody CreateCustomerInvoiceRequest request){
        return customerInvoiceService.createCustomerInvoice(request);
    }
    @DeleteMapping("{id}")
    public void deleteCustomerInvoice(@PathVariable Integer id){
        customerInvoiceService.deleteCustomerInvoice(id);
    }
}
