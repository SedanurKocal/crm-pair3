package com.tcellpair3.customerservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cartservice")
public interface CartClient {
    @GetMapping("/api/v1/cart/{id}")
    void getByIdCart(@PathVariable("id") int cartId);

    @GetMapping("/api/v1/cart/customer/{customerId}/active-products")
    boolean hasActiveProducts(@PathVariable("customerId") int customerId);
}
