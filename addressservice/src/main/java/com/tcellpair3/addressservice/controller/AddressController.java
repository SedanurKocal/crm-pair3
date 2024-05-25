package com.tcellpair3.addressservice.controller;

import com.tcellpair3.addressservice.clients.CustomerClient;
import com.tcellpair3.addressservice.entities.Address;
import com.tcellpair3.addressservice.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressRepository addressRepository;
    private final CustomerClient customerClient;
    @GetMapping("/customer/{customerId}")
    public List<Address> getAddressesByCustomerId(@PathVariable Integer customerId) {
        return addressRepository.findByCustomerId(customerId);
    }

    @PostMapping
    public Address createAddress(@RequestBody Address addresses) {
        boolean customerExists = customerClient.doesCustomerExist(addresses.getCustomerId());
        if (!customerExists) {
            throw new RuntimeException("Customer does not exist");
        }
        return addressRepository.save(addresses);
    }
}
