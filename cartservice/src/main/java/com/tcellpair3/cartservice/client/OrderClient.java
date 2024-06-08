package com.tcellpair3.cartservice.client;

import com.tcellpair3.cartservice.entities.Cart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "orderservice")
public interface OrderClient {
    @PostMapping("/api/v1/orders/save")
    ResponseEntity<String> createOrder(@RequestBody Cart cart);

}