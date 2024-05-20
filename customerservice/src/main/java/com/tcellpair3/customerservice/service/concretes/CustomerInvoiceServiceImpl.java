package com.tcellpair3.customerservice.service.concretes;

import com.tcellpair3.customerservice.core.dtos.requests.customerinvoice.CreateCustomerInvoiceRequest;
import com.tcellpair3.customerservice.core.dtos.requests.customerinvoice.UpdateCustomerInvoiceRequest;
import com.tcellpair3.customerservice.core.dtos.responses.customerinvoice.*;
import com.tcellpair3.customerservice.core.exception.type.BusinessException;
import com.tcellpair3.customerservice.core.mappers.AddressMapper;
import com.tcellpair3.customerservice.core.mappers.CustomerInvoiceMapper;
import com.tcellpair3.customerservice.entities.Customer;
import com.tcellpair3.customerservice.entities.CustomerInvoice;
import com.tcellpair3.customerservice.repositories.CustomerInvoiceRepository;
import com.tcellpair3.customerservice.repositories.CustomerRepository;
import com.tcellpair3.customerservice.service.abstracts.CustomerInvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerInvoiceServiceImpl implements CustomerInvoiceService {

    private final CustomerInvoiceRepository customerInvoiceRepository;
    private final CustomerRepository customerRepository;
    @Override
    public CreateCustomerInvoiceResponse createCustomerInvoice(CreateCustomerInvoiceRequest request) {

        CustomerInvoice customerInvoice = CustomerInvoiceMapper.INSTANCE.createCustomerInvoiceMapper(request);

        Optional<Customer> optionalCustomer = customerRepository.findById(request.getCustomerId());

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();

            customerInvoice.setAccountName(customer.getAccountNumber());

            CustomerInvoice savedCustomerInvoice = customerInvoiceRepository.save(customerInvoice);

            return new CreateCustomerInvoiceResponse(
                    savedCustomerInvoice.getId(),
                    savedCustomerInvoice.getAccountName(),
                    savedCustomerInvoice.getAccountStatus(),
                    savedCustomerInvoice.getAccountType(),
                    savedCustomerInvoice.getCustomer().getId()
            );
        } else {
            throw new BusinessException("Customer not found with ID: " + request.getCustomerId());
        }
    }

    @Override
    public UpdateCustomerInvoiceResponse updateCustomerInvoice(int id, UpdateCustomerInvoiceRequest request) {
        return null;
    }

    @Override
    public void deleteCustomerInvoice(int id) {

    }

    @Override
    public List<GetAllCustomerInvoiceResponse> getAllCustomerInvoice() {
        return null;
    }

    @Override
    public Optional<GetByIdCustomerInvoiceResponse> getByCustomerInvoiceId(int id) {
        return Optional.empty();
    }

    @Override
    public List<CustomerInvoiceWithCustomerResponse> findByCustomerId(Integer customerId) {
        List<CustomerInvoice> customerInvoices = customerInvoiceRepository.findByCustomerId(customerId);
        return CustomerInvoiceMapper.INSTANCE.customerInvoiceWithCustomer(customerInvoices);

    }

    @Override
    public CustomerInvoiceWithAddressResponse findCustomerInvoiceWithAddressesById(Integer invoiceId) {
        CustomerInvoice customerInvoice = customerInvoiceRepository.findCustomerInvoiceWithAddressesById(invoiceId);
        if (customerInvoice == null) {
            return null;
        }

        CustomerInvoiceWithAddressResponse dto = new CustomerInvoiceWithAddressResponse();
        dto.setId(customerInvoice.getId());
        dto.setAccountName(customerInvoice.getAccountName());
        dto.setAccountStatus(customerInvoice.getAccountStatus());
        dto.setAccountType(customerInvoice.getAccountType());
        dto.setCustomerId(customerInvoice.getCustomer().getId());
        dto.setAddresses(AddressMapper.INSTANCE.AddressToListAddressResponses(customerInvoice.getCustomer().getAddresses()));

        return dto;
    }


}
