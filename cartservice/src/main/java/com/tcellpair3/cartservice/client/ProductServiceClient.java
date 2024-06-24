package com.tcellpair3.cartservice.client;

import com.tcellpair3.cartservice.core.configuration.FeignClientConfig;
import com.tcellpair3.cartservice.core.dtos.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "productservice",configuration = FeignClientConfig.class)
public interface ProductServiceClient {
    @GetMapping("/api/v1/products/{id}")
    ProductResponse getProductDetails(@PathVariable("id") Integer productId);

    // controller tarafında controller yazılacak
}