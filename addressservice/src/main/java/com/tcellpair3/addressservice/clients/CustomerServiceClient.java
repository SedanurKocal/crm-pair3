package com.tcellpair3.addressservice.clients;

import com.tcellpair3.addressservice.core.dto.responses.GetAddressByCustomerInvoiceIdResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "customerservice")
public interface CustomerServiceClient {
    @GetMapping("/api/v1/customers/{customerId}/exists")
    boolean doesCustomerExist(@PathVariable("customerId") Integer customerId);

    @GetMapping("/api/v1/customerInvoices/{orderId}/customer")
    ResponseEntity<GetAddressByCustomerInvoiceIdResponse> getCustomerByInvoiceId(@PathVariable Integer orderId);
}
