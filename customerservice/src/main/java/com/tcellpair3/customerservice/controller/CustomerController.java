package com.tcellpair3.customerservice.controller;

import com.tcellpair3.customerservice.core.dtos.requests.customer.CreateCustomerRequest;
import com.tcellpair3.customerservice.core.dtos.requests.customer.UpdateCustomerRequest;
import com.tcellpair3.customerservice.core.dtos.responses.customer.CreateCustomerResponse;
import com.tcellpair3.customerservice.core.dtos.responses.customer.GetAllCustomersResponse;
import com.tcellpair3.customerservice.core.dtos.responses.customer.GetByIdCustomerResponse;
import com.tcellpair3.customerservice.core.dtos.responses.customer.UpdateCustomerResponse;
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
