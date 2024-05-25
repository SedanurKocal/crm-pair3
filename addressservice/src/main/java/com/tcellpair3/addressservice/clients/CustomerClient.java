package com.tcellpair3.addressservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customerservice")
public interface CustomerClient {
    @GetMapping("/api/v1/customers/{customerId}/exists")
    boolean doesCustomerExist(@PathVariable("customerId") Integer customerId);
}
