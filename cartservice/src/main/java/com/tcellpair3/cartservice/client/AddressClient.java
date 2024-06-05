package com.tcellpair3.cartservice.client;

import com.tcellpair3.cartservice.core.dtos.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("addressservice")
public interface AddressClient {
    @GetMapping("/address/{addressId}/exists")
    boolean addressIdExists(@PathVariable int addressId);

    @GetMapping("/address/addressDetail/{addressId}")
    AddressResponse addressDetails(@PathVariable("addressId") Integer addressId);

}
