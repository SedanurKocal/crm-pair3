package com.tcellpair3.customerservice.service.abstracts;

import com.tcellpair3.customerservice.core.dtos.requests.customer.CreateCustomerRequest;
import com.tcellpair3.customerservice.core.dtos.requests.customer.UpdateCustomerRequest;
import com.tcellpair3.customerservice.core.dtos.responses.customer.*;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    CreateCustomerResponse createCustomer(CreateCustomerRequest request) throws Exception;
    UpdateCustomerResponse updateCustomer(int id, UpdateCustomerRequest request) throws Exception;
    void deleteCustomer(int id);
    List<GetAllCustomersResponse> getAllCustomers();
    Optional<GetCustomerByIdResponse> getByCustomerId(int id);
    Page<SearchResultsResponse> getCustomersByFirstName(String firstName, int page, int size);
    List<SearchResultsResponse> findByFirstName(String firstName);
    List<SearchResultsResponse> findByLastName(String lastName);
    List<SearchResultsResponse> findByAccountNumber(Integer accountNumber);
    List<SearchResultsResponse> findByNationalId(String nationalId);
    List<SearchResultsResponse> findByContactMedium_MobilePhone(String mobilePhone);


    boolean existsById(Integer customerId);

    void cartNumber(int cartId);

}
