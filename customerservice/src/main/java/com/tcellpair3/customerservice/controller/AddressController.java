package com.tcellpair3.customerservice.controller;

import com.tcellpair3.customerservice.core.dtos.requests.address.CreateAddressRequest;
import com.tcellpair3.customerservice.core.dtos.requests.address.UpdateAddressRequest;
import com.tcellpair3.customerservice.core.dtos.responses.address.CreateAddressResponse;
import com.tcellpair3.customerservice.core.dtos.responses.address.GetAllAddressResponse;
import com.tcellpair3.customerservice.core.dtos.responses.address.GetByIdAddressResponse;
import com.tcellpair3.customerservice.core.dtos.responses.address.UpdateAddressResponse;
import com.tcellpair3.customerservice.core.dtos.responses.contactmedium.GetAllContactMediumResponse;
import com.tcellpair3.customerservice.service.abstracts.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/address/")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @GetMapping
    public List<GetAllAddressResponse> getAllAddresses(){
        return addressService.getAllAddresses();

    }

    @GetMapping("{id}")
    public Optional<GetByIdAddressResponse> getByIdAddress(@PathVariable int id){
        return addressService.getByIdAddress(id);
    }

    @PostMapping
    public CreateAddressResponse createAddress(@Valid @RequestBody CreateAddressRequest request){
        return addressService.createAddress(request);
    }

    @PutMapping("{id}")
    public UpdateAddressResponse updateAddress(@PathVariable int id, @RequestBody UpdateAddressRequest request){
        return addressService.updateAddress(id,request);
    }

    @DeleteMapping("{id}")
    public void deleteAddress(@PathVariable int id){
        addressService.deleteAddress(id);
    }
}
