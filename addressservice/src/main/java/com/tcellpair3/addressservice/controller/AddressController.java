package com.tcellpair3.addressservice.controller;

import com.tcellpair3.addressservice.clients.CustomerServiceClient;
import com.tcellpair3.addressservice.core.dto.requests.CreateAddressRequest;
import com.tcellpair3.addressservice.core.dto.requests.UpdateAddressRequest;
import com.tcellpair3.addressservice.core.dto.responses.*;
import com.tcellpair3.addressservice.service.abstracts.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;
    private final CustomerServiceClient customerServiceClient;

    @GetMapping("/customer/{customerId}")
    public List<GetAddressByCustomerIdResponse> getAddressesByCustomerId(@PathVariable Integer customerId) {
        return addressService.getAddressesByCustomerId(customerId);
    }

    @GetMapping()
    public List<GetAllAddressResponse> getAllAddress() {
        return addressService.getAllAddress();
    }

    @GetMapping("/{id}")
    public Optional<GetByAddressIdResponse> findByIdAddress(@PathVariable("id") int id) {
        return addressService.getByIdAddress(id);
    }

    @GetMapping("/addressDetail/{addressId}")
    public AddressResponse addressDetails(@PathVariable Integer addressId) {
        return addressService.getAddressDetails(addressId);
    }

    @PostMapping
    public CreateAddressResponse createAddress(@Valid @RequestBody CreateAddressRequest request) {
        return addressService.createAddress(request);
    }

    @PutMapping("/{id}")
    public UpdateAddressResponse updateAddress(@PathVariable int id, @Valid @RequestBody UpdateAddressRequest address) {
        return addressService.updateAddress(id, address);
    }

    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable int id) {
        addressService.deleteAddress(id);
    }

    @GetMapping("/{addressId}/exists")
    public boolean addressIdExists(@PathVariable int addressId) {
        return addressService.existById(addressId);
    }

    @GetMapping("/customerInvoice/{customerInvoiceId}")
    public ResponseEntity<List<AddressResponse>> getAddressesByCustomerInvoiceId(@PathVariable Integer customerInvoiceId) {
        List<AddressResponse> addresses = addressService.getAddressesByCustomerInvoiceId(customerInvoiceId);
        if (addresses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(addresses);
    }

    @PostMapping("/setDefault")
    public ResponseEntity<Void> setDefaultAddress(@RequestParam int customerId, @RequestParam int addressId) {
        addressService.setDefaultAddress(customerId, addressId);
        return ResponseEntity.ok().build();
    }
}


