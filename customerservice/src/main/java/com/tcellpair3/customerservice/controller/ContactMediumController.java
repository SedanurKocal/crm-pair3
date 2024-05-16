package com.tcellpair3.customerservice.controller;

import com.tcellpair3.customerservice.core.dtos.requests.contactmedium.CreateContactMediumRequest;
import com.tcellpair3.customerservice.core.dtos.requests.contactmedium.UpdateContactMediumRequest;
import com.tcellpair3.customerservice.core.dtos.responses.contactmedium.CreateContactMediumResponse;
import com.tcellpair3.customerservice.core.dtos.responses.contactmedium.GetAllContactMediumResponse;
import com.tcellpair3.customerservice.core.dtos.responses.contactmedium.GetByIdContactMediumResponse;
import com.tcellpair3.customerservice.core.dtos.responses.contactmedium.UpdateContactMediumResponse;
import com.tcellpair3.customerservice.repositories.ContactMediumRepository;
import com.tcellpair3.customerservice.service.abstracts.ContactMediumService;
import jakarta.validation.Valid;
import jakarta.ws.rs.Path;
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
    public Optional<GetByIdContactMediumResponse> getByIdContactMedium(@PathVariable int id)
    {
        return contactMediumService.getByContactMediumId(id);
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
