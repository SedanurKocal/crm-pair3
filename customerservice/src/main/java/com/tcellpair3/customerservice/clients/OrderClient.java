package com.tcellpair3.customerservice.clients;

import com.tcellpair3.customerservice.core.configuration.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "orderservice",configuration = FeignClientConfig.class)
public interface OrderClient {

    @GetMapping("/api/v1/orders/customer/{customerInvoiceId}/active-products")
    boolean hasActiveProducts(@PathVariable("customerInvoiceId") int customerInvoiceId);
}
