package com.tcellpair3.customerservice.controller;

import com.tcellpair3.customerservice.core.dtos.requests.customer.CreateCustomerRequest;
import com.tcellpair3.customerservice.core.dtos.requests.customer.UpdateCustomerRequest;
import com.tcellpair3.customerservice.core.dtos.responses.customer.*;
import com.tcellpair3.customerservice.entities.Customer;
import com.tcellpair3.customerservice.service.abstracts.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/customers/")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public List<GetAllCustomersResponse> getAllCustomers(){
        return customerService.getAllCustomers();
    }
    @GetMapping("{id}")
    public Optional<GetByIdCustomerResponse> getByIdCustomers(@PathVariable int id){
        return customerService.getByCustomerId(id);
    }
    @GetMapping("FindByStartName")
    public List<GetAllCustomersResponse> getByLikeName(@RequestParam(required = false) String startName)
    {
        return customerService.findByFirstNameStartingWithIgnoreCase(startName);
    }
    @GetMapping("findByFirstName")
    public List<SearchResultsResponse> findByFirstName(String firstName)
    {
        return customerService.findByFirstName(firstName);
    }
    @GetMapping("findByLastName")
    public List<SearchResultsResponse> findByLastName(String lastName)
    {
        return customerService.findByLastName(lastName);
    }
    @GetMapping("findByAccountNumber")
    public List<SearchResultsResponse> findByAccountNumber(Integer accountNumber)
    {
        return customerService.findByAccountNumber(accountNumber);
    }
    @GetMapping("findByNationalId")
    public List<SearchResultsResponse> findByNationalId(String nationalId)
    {
        return customerService.findByNationalId(nationalId);
    }
    @GetMapping("findByMobilePhone")
    public List<SearchResultsResponse> findByMobilePhome(String mobilePhone)
    {
        return customerService.findByContactMedium_MobilePhone(mobilePhone);
    }

    @PostMapping
    public CreateCustomerResponse createCustomer(@Valid @RequestBody CreateCustomerRequest request){
        return customerService.createCustomer(request);
    }

    @DeleteMapping("{id}")
    public void deleteCustomer(@PathVariable int id){
        customerService.deleteCustomer(id);
    }
    @PutMapping("{id}")
    public UpdateCustomerResponse deleteCustomer(@PathVariable int id, @RequestBody @Valid UpdateCustomerRequest request){
        return customerService.updateCustomer(id,request);
    }



}
