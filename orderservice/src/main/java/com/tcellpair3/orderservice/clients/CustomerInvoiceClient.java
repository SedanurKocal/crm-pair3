package com.tcellpair3.orderservice.clients;

import com.tcellpair3.orderservice.core.configuration.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customerservice",configuration = FeignClientConfig.class)
public interface CustomerInvoiceClient {
    @GetMapping("/api/v1/customerInvoices/{customerInvoiceId}/exists")
    boolean doesCustomerInvoiceExist(@PathVariable Integer customerInvoiceId);
}
