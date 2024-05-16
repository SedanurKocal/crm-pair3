package com.tcellpair3.customerservice.service.abstracts;

import com.tcellpair3.customerservice.core.dtos.requests.customer.CreateCustomerRequest;
import com.tcellpair3.customerservice.core.dtos.requests.customer.UpdateCustomerRequest;
import com.tcellpair3.customerservice.core.dtos.responses.customer.CreateCustomerResponse;
import com.tcellpair3.customerservice.core.dtos.responses.customer.GetAllCustomersResponse;
import com.tcellpair3.customerservice.core.dtos.responses.customer.GetByIdCustomerResponse;
import com.tcellpair3.customerservice.core.dtos.responses.customer.UpdateCustomerResponse;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    CreateCustomerResponse createCustomer(CreateCustomerRequest request);
    UpdateCustomerResponse updateCustomer(int id, UpdateCustomerRequest request);
    void deleteCustomer(int id);
    List<GetAllCustomersResponse> getAllCustomers();
    Optional<GetByIdCustomerResponse> getByCustomerId(int id);


}
