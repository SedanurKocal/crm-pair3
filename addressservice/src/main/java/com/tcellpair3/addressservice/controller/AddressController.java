package com.tcellpair3.addressservice.controller;

import com.tcellpair3.addressservice.clients.CustomerServiceClient;
import com.tcellpair3.addressservice.core.dto.responses.AddressDtoResponse;
import com.tcellpair3.addressservice.entities.Address;
import com.tcellpair3.addressservice.service.abstracts.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;
    private final CustomerServiceClient customerServiceClient;
    @GetMapping("/customer/{customerId}")
    public List<Address> getAddressesByCustomerId(@PathVariable Integer customerId) {
        return addressService.getAddressesByCustomerId(customerId);
    }
    @GetMapping("/findAllAddress")
    public List<Address> getAllAddress()
    {
        return addressService.getAllAddress();
    }
    @GetMapping("/findByIdAddress/{id}")
    public Optional<Address> findByIdAddress(@PathVariable int id)
    {
        return addressService.getByIdAddress(id);
    }
    @PostMapping
    public AddressDtoResponse createAddress(@Valid @RequestBody AddressDtoResponse request) {

        return addressService.createAddress(request);
    }
    @PutMapping("{id}")
    public Address updateAddress(@PathVariable int id,@Valid @RequestBody Address address)
    {

        return addressService.updateAddress(id,address);
    }
    @DeleteMapping("{id}")
    public void deleteAddress(@PathVariable int id){
        addressService.deleteAddress(id);
    }
}
