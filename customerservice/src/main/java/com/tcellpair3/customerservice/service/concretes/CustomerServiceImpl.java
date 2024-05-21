package com.tcellpair3.customerservice.service.concretes;

import com.tcellpair3.customerservice.core.dtos.requests.customer.CreateCustomerRequest;
import com.tcellpair3.customerservice.core.dtos.requests.customer.UpdateCustomerRequest;
import com.tcellpair3.customerservice.core.dtos.responses.address.GetAllAddressResponse;
import com.tcellpair3.customerservice.core.dtos.responses.customer.*;
import com.tcellpair3.customerservice.core.exception.type.BusinessException;
import com.tcellpair3.customerservice.core.mappers.AddressMapper;
import com.tcellpair3.customerservice.core.mappers.CustomerMapper;
import com.tcellpair3.customerservice.core.service.Abstract.ContactMediumValidationService;
import com.tcellpair3.customerservice.core.service.Concrete.CustomerValidationServiceImpl;
import com.tcellpair3.customerservice.entities.Address;
import com.tcellpair3.customerservice.entities.Customer;
import com.tcellpair3.customerservice.entities.CustomerInvoice;
import com.tcellpair3.customerservice.repositories.CustomerInvoiceRepository;
import com.tcellpair3.customerservice.repositories.CustomerRepository;
import com.tcellpair3.customerservice.service.abstracts.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerValidationServiceImpl customerValidationService;
    private final ContactMediumValidationService contactMediumValidationService;
    private final CustomerInvoiceRepository customerInvoiceRepository;
    @Override
    public CreateCustomerResponse createCustomer(CreateCustomerRequest request) {
        boolean hasNationalId =customerRepository.existsByNationalId(request.getNationalId());
        if(hasNationalId)
        {
            throw new BusinessException("A customer is already exist with this Nationality ID");
        }
        customerValidationService.validateBirthdate(request.getBirthdate());
        customerValidationService.isValidTC(request.getNationalId());
        // Birthday check
        Customer customer= CustomerMapper.INSTANCE.createCustomerMapper(request);
        Customer saveCustomer = customerRepository.save(customer);

        return new CreateCustomerResponse(
                saveCustomer.getId(),
                saveCustomer.getAccountNumber(),
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

        List<CustomerInvoice> invoices = customerInvoiceRepository.findByCustomerId(customerOptional.get().getId());
        for (CustomerInvoice invoice : invoices) {
            invoice.setAccountName(request.getAccountNumber());
            customerInvoiceRepository.save(invoice);
        }
        customerValidationService.validateBirthdate(request.getBirthdate());
        customerValidationService.isValidTC(request.getNationalId());
        Customer customer = CustomerMapper.INSTANCE.updateCustomerMapper(request,existingCustomer);
        Customer saveCustomer=customerRepository.save(customer);

        return new UpdateCustomerResponse(
                saveCustomer.getId(),
                saveCustomer.getAccountNumber(),
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

    @Override
    public Page<SearchResultsResponse> getCustomersByFirstName(String firstName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Customer> customerPage = customerRepository.findByFirstNameStartingWithIgnoreCase(firstName, pageable);
        return customerPage.map(CustomerMapper.INSTANCE::searchResultResponse);
    }
    @Override
    public List<SearchResultsResponse> findByFirstName(String firstName) {
        List<Customer> customers = customerRepository.findByFirstName(firstName);
        if(customers.isEmpty())
        {
            throw new BusinessException("No customer found! Would you like to create the customer?");
        }
        return customers.stream()
                .map(CustomerMapper.INSTANCE::searchResultResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<SearchResultsResponse> findByLastName(String lastName) {
        List<Customer> customers = customerRepository.findByLastName(lastName);
        if(customers.isEmpty())
        {
            throw new BusinessException("No customer found! Would you like to create the customer?");
        }
        return customers.stream()
                .map(CustomerMapper.INSTANCE::searchResultResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<SearchResultsResponse> findByAccountNumber(Integer accountNumber) {
        List<Customer> customers = customerRepository.findByAccountNumber(accountNumber);
        if(customers.isEmpty())
        {
            throw new BusinessException("No customer found! Would you like to create the customer?");
        }
        return customers.stream()
                .map(CustomerMapper.INSTANCE::searchResultResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<SearchResultsResponse> findByNationalId(String nationalId) {
        List<Customer> customers = customerRepository.findByNationalId(nationalId);
        if(customers.isEmpty())
        {
            throw new BusinessException("No customer found! Would you like to create the customer?");
        }
        return customers.stream()
                .map(CustomerMapper.INSTANCE::searchResultResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<SearchResultsResponse> findByContactMedium_MobilePhone(String mobilePhone) {
        contactMediumValidationService.validatePhoneNumber(mobilePhone);
        List<Customer> customers = customerRepository.findByContactMedium_MobilePhone(mobilePhone);
        if(customers.isEmpty())
        {
            throw new BusinessException("No customer found! Would you like to create the customer?");
        }
        return customers.stream()
                .map(CustomerMapper.INSTANCE::searchResultResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<GetAllAddressResponse> findAddressesByCustomerId(Integer customerId) {
        List<Address> addresses = customerRepository.findAddressesByCustomerId(customerId);
        return AddressMapper.INSTANCE.AddressToListAddressResponses(addresses);
    }


}
