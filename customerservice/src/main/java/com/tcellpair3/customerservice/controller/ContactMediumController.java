package com.tcellpair3.customerservice.controller;

import com.tcellpair3.customerservice.core.dtos.requests.contactmedium.CreateContactMediumRequest;
import com.tcellpair3.customerservice.core.dtos.requests.contactmedium.UpdateContactMediumRequest;
import com.tcellpair3.customerservice.core.dtos.responses.contactmedium.*;
import com.tcellpair3.customerservice.service.abstracts.ContactMediumService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/contactMedium/")
@RequiredArgsConstructor
public class ContactMediumController {

    private final ContactMediumService contactMediumService;


    @GetMapping
    public List<GetAllContactMediumResponse> getAllContactMedium()
    {
        return contactMediumService.getAllContactMedium();
    }
    @GetMapping("{id}")
    public Optional<GetContactMediumByIdResponse> getByIdContactMedium(@PathVariable int id)
    {
        return contactMediumService.getByContactMediumId(id);
    }
    @GetMapping("/ContactMediumWithCustomer/{customerId}")
    public List<ContactMediumWithCustomerResponse> findByCustomerId(@PathVariable Integer customerId)
    {
        return contactMediumService.findByCustomerId(customerId);
    }
    @PostMapping
    public CreateContactMediumResponse createContactMedium(@Valid @RequestBody CreateContactMediumRequest request) {
        return contactMediumService.createContactMedium(request);
    }

    @PutMapping("{id}")
    public UpdateContactMediumResponse updateContactMedium(@PathVariable int id, @Valid @RequestBody UpdateContactMediumRequest request) {
        return contactMediumService.updateContactMedium(id, request);
    }

    @DeleteMapping("{id}")
    public void deleteContactMedium(@PathVariable int id) {
        contactMediumService.deleteContactMedium(id);
    }
}
