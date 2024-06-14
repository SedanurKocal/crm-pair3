package com.tcellpair3.customerservice.controller;

import com.tcellpair3.customerservice.core.dtos.requests.customer.CreateCustomerRequest;
import com.tcellpair3.customerservice.core.dtos.requests.customer.UpdateCustomerRequest;
import com.tcellpair3.customerservice.core.dtos.responses.customer.CreateCustomerResponse;
import com.tcellpair3.customerservice.core.dtos.responses.customer.GetAllCustomersResponse;
import com.tcellpair3.customerservice.core.dtos.responses.customer.GetCustomerByIdResponse;
import com.tcellpair3.customerservice.core.dtos.responses.customer.SearchResultsResponse;
import com.tcellpair3.customerservice.core.dtos.responses.customer.UpdateCustomerResponse;
import com.tcellpair3.customerservice.service.abstracts.CustomerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class CustomerController {
    private final CustomerService customerService;


    @GetMapping("getAll")
    public List<GetAllCustomersResponse> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("{id}")
    public Optional<GetCustomerByIdResponse> getByIdCustomers(@PathVariable int id) {
        return customerService.getByCustomerId(id);
    }

    @GetMapping("/getCustomersByFirstName")
    public Page<SearchResultsResponse> getCustomersByFirstName(
            @RequestParam String firstName,
            @RequestParam int page,
            @RequestParam int size) {
        return customerService.getCustomersByFirstName(firstName, page, size);
    }

    @GetMapping("findByFirstName")
    public List<SearchResultsResponse> findByFirstName(String firstName) {
        return customerService.findByFirstName(firstName);
    }

    @GetMapping("findByLastName")
    public List<SearchResultsResponse> findByLastName(String lastName) {
        return customerService.findByLastName(lastName);
    }

    @GetMapping("findByAccountNumber")
    public List<SearchResultsResponse> findByAccountNumber(Integer accountNumber) {
        return customerService.findByAccountNumber(accountNumber);
    }

    @GetMapping("findByNationalId")
    public List<SearchResultsResponse> findByNationalId(String nationalId) {
        return customerService.findByNationalId(nationalId);
    }

    @GetMapping("findByMobilePhone")
    public List<SearchResultsResponse> findByMobilePhone(String mobilePhone) {
        return customerService.findByContactMedium_MobilePhone(mobilePhone);
    }


    @GetMapping("/{customerId}/exists")
    public boolean doesCustomerExist(@PathVariable Integer customerId) {
        return customerService.existsById(customerId);
    }

    @GetMapping("/{id}/cart")
    public void cartNumber(@PathVariable("id") Integer cartId) {
        customerService.cartNumber(cartId);
    }

    @PostMapping
    public CreateCustomerResponse createCustomer(@Valid @RequestBody CreateCustomerRequest request) throws Exception {
        return customerService.createCustomer(request);
    }

    @DeleteMapping("{id}")
    public void deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
    }

    @PutMapping("{id}")
    public UpdateCustomerResponse updateCustomer(@PathVariable int id, @RequestBody @Valid UpdateCustomerRequest request) throws Exception {
        return customerService.updateCustomer(id, request);
    }
}
