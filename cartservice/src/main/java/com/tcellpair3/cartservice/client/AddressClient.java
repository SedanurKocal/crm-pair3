package com.tcellpair3.cartservice.client;

import com.tcellpair3.cartservice.core.dtos.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("addressservice")
public interface AddressClient {
    @GetMapping("/api/v1/addresses/{addressId}/exists")
    boolean doesAddressExist(@PathVariable int addressId);

    @GetMapping("/api/v1/addresses/addressDetail/{addressId}")
    AddressResponse addressDetails(@PathVariable("addressId") Integer addressId);

}
