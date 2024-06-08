package com.tcellpair3.cartservice.client;

import com.tcellpair3.cartservice.core.configuration.FeignClientConfig;
import com.tcellpair3.cartservice.core.dtos.CustomerWithCustomerInvoiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customerservice",configuration = FeignClientConfig.class)
public interface CustomerInvoiceClient {
    @GetMapping("/api/v1/customerInvoices/{customerInvoiceId}/exists")
    ResponseEntity<Boolean> doesCustomerInvoiceExist(@PathVariable("customerInvoiceId") Integer customerInvoiceId);

    @GetMapping("/api/v1/customerInvoices/{orderId}/customer")
    ResponseEntity<CustomerWithCustomerInvoiceResponse> getCustomerByInvoiceId(@PathVariable("orderId") Integer orderId);
}