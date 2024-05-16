package com.tcellpair3.customerservice.service.concretes;

import com.tcellpair3.customerservice.core.dtos.requests.customer.CreateCustomerRequest;
import com.tcellpair3.customerservice.core.dtos.requests.customer.UpdateCustomerRequest;
import com.tcellpair3.customerservice.core.dtos.responses.customer.CreateCustomerResponse;
import com.tcellpair3.customerservice.core.dtos.responses.customer.GetAllCustomersResponse;
import com.tcellpair3.customerservice.core.dtos.responses.customer.GetByIdCustomerResponse;
import com.tcellpair3.customerservice.core.dtos.responses.customer.UpdateCustomerResponse;
import com.tcellpair3.customerservice.core.exception.type.BusinessException;
import com.tcellpair3.customerservice.core.mappers.CustomerMapper;
import com.tcellpair3.customerservice.core.service.Concrete.CustomerValidationServiceImpl;
import com.tcellpair3.customerservice.entities.Customer;
import com.tcellpair3.customerservice.repositories.CustomerRepository;
import com.tcellpair3.customerservice.service.abstracts.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerValidationServiceImpl customerValidationService;
    @Override
    public CreateCustomerResponse createCustomer(CreateCustomerRequest request) {
        boolean hasNationalId =customerRepository.existsByNationalId(request.getNationalId());
        if(hasNationalId)
        {
            throw new BusinessException("A customer is already exist with this Nationality ID");
        }
        customerValidationService.validateBirthdate(request.getBirthdate());
        // Birthday check
        Customer customer= CustomerMapper.INSTANCE.createCustomerMapper(request);
        Customer saveCustomer = customerRepository.save(customer);

        return new CreateCustomerResponse(
                saveCustomer.getId(),
                saveCustomer.getFirstName(),
                saveCustomer.getLastName(),
                saveCustomer.getMiddleName(),
                saveCustomer.getNationalId(),
                saveCustomer.getMotherName(),
                saveCustomer.getFatherName(),
                saveCustomer.getBirthdate(),
                saveCustomer.getGender()
        );

    }

    @Override
    public UpdateCustomerResponse updateCustomer(int id, UpdateCustomerRequest request) {
        boolean hasNationalId =customerRepository.existsByNationalId(request.getNationalId());
        if(hasNationalId)
        {
            throw new BusinessException("A customer is already exist with this Nationality ID");
        }
        Optional<Customer> customerOptional = customerRepository.findById(id);
        Customer existingCustomer = customerOptional.get();
        Customer customer = CustomerMapper.INSTANCE.updateCustomerMapper(request,existingCustomer);
        Customer saveCustomer=customerRepository.save(customer);

        return new UpdateCustomerResponse(
                saveCustomer.getId(),
                saveCustomer.getFirstName(),
                saveCustomer.getLastName(),
                saveCustomer.getMiddleName(),
                saveCustomer.getNationalId(),
                saveCustomer.getMotherName(),
                saveCustomer.getFatherName(),
                saveCustomer.getBirthdate(),
                saveCustomer.getGender()

                );

    }

    @Override
    public void deleteCustomer(int id) {

        customerRepository.deleteById(id);

    }

    @Override
    public List<GetAllCustomersResponse> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return CustomerMapper.INSTANCE.customersToListCustomerResponses(customers);
    }

    @Override
    public Optional<GetByIdCustomerResponse> getByCustomerId(int id) {
        Optional<Customer> customerOptional= customerRepository.findById(id);
        return customerOptional.map(CustomerMapper.INSTANCE::getByIdCustomerMapper);
    }

}
