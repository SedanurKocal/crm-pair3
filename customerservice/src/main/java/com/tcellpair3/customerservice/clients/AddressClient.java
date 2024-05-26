package com.tcellpair3.customerservice.clients;

import com.tcellpair3.addressservice.entities.Address;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "addressservice")
public interface AddressClient {
    @GetMapping("/address/customer/{customerId}")
    List<Address> getAddressesByCustomerId(@PathVariable("customerId") Integer customerId);

    @PostMapping("/address")
    Address createAddress(@RequestBody Address address);

    @PutMapping("{id}")
    public Address updateAddress(@PathVariable int id, @Valid @RequestBody Address address);
}
