package com.tcellpair3.addressservice.clients;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "customerservice")
public interface CustomerServiceClient {
    @GetMapping("/api/v1/customers/{customerId}/exists")
    boolean doesCustomerExist(@PathVariable("customerId") Integer customerId);

//    @GetMapping("/api/v1/customerInvoices/{invoiceId}/customer")
//    ResponseEntity<CustomerWithCustomerInvoiceResponse> getCustomerByInvoiceId(@PathVariable Integer invoiceId);
}
