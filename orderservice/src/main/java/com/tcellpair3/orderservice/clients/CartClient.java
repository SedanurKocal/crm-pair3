package com.tcellpair3.orderservice.clients;

import com.tcellpair3.orderservice.core.configuration.FeignClientConfig;
import com.tcellpair3.orderservice.core.dtos.response.CartResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.CacheResponse;
import java.util.List;

@FeignClient(name = "cartservice",configuration = FeignClientConfig.class)
public interface CartClient {
    @GetMapping("/api/v1/carts/customerInvoice/{customerInvoiceId}")
    ResponseEntity<List<CartResponse>> getCartsByCustomerInvoiceId(@PathVariable("customerInvoiceId") int customerInvoiceId);

}
