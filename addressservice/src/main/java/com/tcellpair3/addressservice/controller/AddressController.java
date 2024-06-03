package com.tcellpair3.addressservice.controller;

import com.tcellpair3.addressservice.clients.CustomerServiceClient;
import com.tcellpair3.addressservice.core.dto.requests.CreateAddressRequest;
import com.tcellpair3.addressservice.core.dto.requests.UpdateAddressRequest;
import com.tcellpair3.addressservice.core.dto.responses.*;
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
    public List<GetAddressCustomerById> getAddressesByCustomerId(@PathVariable Integer customerId) {
        return addressService.getAddressesByCustomerId(customerId);
    }
    @GetMapping()
    public List<GetAllAddressResponse> getAllAddress()
    {
        return addressService.getAllAddress();
    }
    @GetMapping("/{id}")
    public Optional<GetByAddressIdResponse> findByIdAddress(@PathVariable int id)
    {
        return addressService.getByIdAddress(id);
    }
    @PostMapping
    public CreateAddressResponse createAddress(@Valid @RequestBody CreateAddressRequest request) {

        return addressService.createAddress(request);
    }
    @PutMapping("/{id}")
    public UpdateAddressResponse updateAddress(@PathVariable int id, @Valid @RequestBody UpdateAddressRequest address)
    {

        return addressService.updateAddress(id,address);
    }
    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable int id){
        addressService.deleteAddress(id);
    }
}
