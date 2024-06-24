package com.tcellpair3.customerservice.service.concretes;

import com.tcellpair3.customerservice.clients.CartClient;
import com.tcellpair3.customerservice.core.dtos.requests.customer.CreateCustomerRequest;
import com.tcellpair3.customerservice.core.dtos.requests.customer.UpdateCustomerRequest;
import com.tcellpair3.customerservice.core.dtos.responses.customer.*;
import com.tcellpair3.customerservice.core.mappers.CustomerMapper;
import com.tcellpair3.customerservice.core.mernis.IRKKPSPublicSoap;
import com.tcellpair3.customerservice.core.service.abstracts.ContactMediumValidationService;
import com.tcellpair3.customerservice.core.service.concretes.CustomerValidationServiceImpl;
import com.tcellpair3.customerservice.entities.Customer;
import com.tcellpair3.customerservice.entities.CustomerInvoice;
import com.tcellpair3.customerservice.repositories.CustomerInvoiceRepository;
import com.tcellpair3.customerservice.repositories.CustomerRepository;
import com.tcellpair3.customerservice.service.abstracts.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.turkcell.tcell.exception.exceptions.type.BaseBusinessException;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerValidationServiceImpl customerValidationService;
    private final ContactMediumValidationService contactMediumValidationService;
    private final CustomerInvoiceRepository customerInvoiceRepository;
    private final CartClient cartClient;
   // private final AddressClient addressClient;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerValidationServiceImpl customerValidationService, ContactMediumValidationService contactMediumValidationService, CustomerInvoiceRepository customerInvoiceRepository, CartClient cartClient) {
        this.customerRepository = customerRepository;
        this.customerValidationService = customerValidationService;
        this.contactMediumValidationService = contactMediumValidationService;
        this.customerInvoiceRepository = customerInvoiceRepository;
        this.cartClient = cartClient;
    }

    @Override
    public CreateCustomerResponse createCustomer(CreateCustomerRequest request) throws Exception {

        IRKKPSPublicSoap client = new IRKKPSPublicSoap();

        boolean isRealPerson = client.TCKimlikNoDogrula(
                Long.valueOf(request.getNationalId()),
                request.getFirstName(),
                request.getLastName(),
                Integer.valueOf(request.getBirthdate().getYear())
        );

        if(isRealPerson){
            boolean hasNationalId =customerRepository.existsByNationalId(request.getNationalId());
            if(hasNationalId)
            {
                throw new BaseBusinessException("A customer is already exist with this Nationality ID");
            }
            customerValidationService.validateBirthdate(request.getBirthdate());

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

        else {
            throw new IllegalArgumentException("Kullanıcı bulunamadı");
        }


    }

    @Override
    public UpdateCustomerResponse updateCustomer(int id, UpdateCustomerRequest request) throws Exception {
        IRKKPSPublicSoap client = new IRKKPSPublicSoap();

        boolean isRealPerson = client.TCKimlikNoDogrula(
                Long.valueOf(request.getNationalId()),
                request.getFirstName(),
                request.getLastName(),
                Integer.valueOf(request.getBirthdate().getYear())
        );

        if (!isRealPerson) {
            throw new BaseBusinessException("Kullanıcı bulunamadı");
        }

        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isEmpty()) {
            throw new BaseBusinessException("Kullanıcı Bulunamadı");
        }

        Customer existingCustomer = customerOptional.get();

        // Aynı T.C. Kimlik numarasına sahip başka bir müşteri olup olmadığını kontrol edin
        List<Customer> customersWithSameNationalId = customerRepository.findByNationalId(request.getNationalId());
        for (Customer customer : customersWithSameNationalId) {
            if (customer.getId() != existingCustomer.getId()) {
                throw new BaseBusinessException("A customer is already exist with this Nationality ID");
            }
        }

        List<CustomerInvoice> invoices = customerInvoiceRepository.findByCustomerId(existingCustomer.getId());
        for (CustomerInvoice invoice : invoices) {
            invoice.setAccountName(request.getAccountNumber());
            customerInvoiceRepository.save(invoice);
        }

        customerValidationService.validateBirthdate(request.getBirthdate());
        customerValidationService.isValidTC(request.getNationalId());
        Customer customer = CustomerMapper.INSTANCE.updateCustomerMapper(request, existingCustomer);
        Customer savedCustomer = customerRepository.save(customer);

        return new UpdateCustomerResponse(
                savedCustomer.getId(),
                savedCustomer.getAccountNumber(),
                savedCustomer.getFirstName(),
                savedCustomer.getLastName(),
                savedCustomer.getMiddleName(),
                savedCustomer.getNationalId(),
                savedCustomer.getMotherName(),
                savedCustomer.getFatherName(),
                savedCustomer.getBirthdate(),
                savedCustomer.getGender()
        );
    }

    @Override
    public void deleteCustomer(int id) {
        boolean customerActiveProduct = cartClient.hasActiveProducts(id);
        if(customerActiveProduct)
        {
            throw new BaseBusinessException("Since the customer has active products, the customer cannot be deleted.");
        }
        customerRepository.deleteById(id);
        throw new BaseBusinessException("Customer Deleted Successfully.");
    }

    @Override
    public List<GetAllCustomersResponse> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return CustomerMapper.INSTANCE.customersToListCustomerResponses(customers);
    }

    @Override
    public Optional<GetCustomerByIdResponse> getByCustomerId(int id) {
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
            throw new BaseBusinessException("No customer found! Would you like to create the customer?");
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
            throw new BaseBusinessException("No customer found! Would you like to create the customer?");
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
            throw new BaseBusinessException("No customer found! Would you like to create the customer?");
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
            throw new BaseBusinessException("No customer found! Would you like to create the customer?");
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
            throw new BaseBusinessException("No customer found! Would you like to create the customer?");
        }
        return customers.stream()
                .map(CustomerMapper.INSTANCE::searchResultResponse)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsById(Integer customerId) {
        return customerRepository.existsById(customerId);
    }

    @Override
    public void cartNumber(int cartId) {
        cartClient.getByIdCart(cartId);
    }
}
