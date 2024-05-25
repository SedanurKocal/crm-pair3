package com.tcellpair3.customerservice.clients;

import com.tcellpair3.addressservice.entities.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "addressservice")
public interface AddressClient {
    @GetMapping("/address/customer/{customerId}")
    List<Address> getAddressesByCustomerId(@PathVariable("customerId") Integer customerId);

    @PostMapping("/address")
    Address createAddress(@RequestBody Address address);
}
