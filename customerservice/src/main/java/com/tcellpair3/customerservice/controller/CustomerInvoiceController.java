package com.tcellpair3.customerservice.controller;

import com.tcellpair3.customerservice.core.dtos.requests.customerinvoice.CreateCustomerInvoiceRequest;
import com.tcellpair3.customerservice.core.dtos.responses.customer.CustomerWithCustomerInvoiceResponse;
import com.tcellpair3.customerservice.core.dtos.responses.customerinvoice.CreateCustomerInvoiceResponse;
import com.tcellpair3.customerservice.core.dtos.responses.customerinvoice.CustomerInvoiceWithCustomerResponse;
import com.tcellpair3.customerservice.core.dtos.responses.customerinvoice.GetCustomerInvoiceByIdResponse;
import com.tcellpair3.customerservice.service.abstracts.CustomerInvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{customerInvoiceId}/exists")
    public ResponseEntity<Boolean> doesCustomerInvoiceExist(@PathVariable Integer customerInvoiceId) {
        return ResponseEntity.ok(customerInvoiceService.existsById(customerInvoiceId));
    }
//TODO: GET CUSTOMER BY ORDER NUMBER FR-3
    @GetMapping("/{orderId}/customer")
    public ResponseEntity<CustomerWithCustomerInvoiceResponse> getCustomerByOrderId(@PathVariable Integer orderId) {
        CustomerWithCustomerInvoiceResponse response = customerInvoiceService.getCustomerByOrderId(orderId);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public CreateCustomerInvoiceResponse createCustomerInvoice(@Valid @RequestBody CreateCustomerInvoiceRequest request){
        return customerInvoiceService.createCustomerInvoice(request);
    }
    @DeleteMapping("{id}")
    public void deleteCustomerInvoice(@PathVariable Integer id){
        customerInvoiceService.deleteCustomerInvoice(id);
    }
}
