package com.tcellpair3.customerservice.service.concretes;

import com.tcellpair3.customerservice.clients.CartClient;
import com.tcellpair3.customerservice.clients.OrderClient;
import com.tcellpair3.customerservice.core.dtos.requests.customerinvoice.CreateCustomerInvoiceRequest;
import com.tcellpair3.customerservice.core.dtos.requests.customerinvoice.UpdateCustomerInvoiceRequest;
import com.tcellpair3.customerservice.core.dtos.responses.customer.CustomerWithCustomerInvoiceResponse;
import com.tcellpair3.customerservice.core.dtos.responses.customerinvoice.*;
import com.tcellpair3.customerservice.core.mappers.CustomerInvoiceMapper;
import com.tcellpair3.customerservice.core.mappers.CustomerMapper;
import com.tcellpair3.customerservice.entities.Customer;
import com.tcellpair3.customerservice.entities.CustomerInvoice;
import com.tcellpair3.customerservice.repositories.CustomerInvoiceRepository;
import com.tcellpair3.customerservice.repositories.CustomerRepository;
import com.tcellpair3.customerservice.service.abstracts.CustomerInvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.turkcell.tcell.exception.exceptions.type.BaseBusinessException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerInvoiceServiceImpl implements CustomerInvoiceService {

    private final CustomerInvoiceRepository customerInvoiceRepository;
    private final CustomerRepository customerRepository;
    private final CartClient cartClient;
    private final OrderClient orderClient;
    //private final AddressClient addressClient;

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
        }
        else {
            throw new BaseBusinessException("Customer not found with ID: " + request.getCustomerId());
        }
    }

    @Override
    public UpdateCustomerInvoiceResponse updateCustomerInvoice(int id, UpdateCustomerInvoiceRequest request) {

        Optional<CustomerInvoice> optionalCustomerInvoice = customerInvoiceRepository.findById(id);
        CustomerInvoice existingCustomerInvoice = optionalCustomerInvoice.get();
        CustomerInvoice customerInvoice = CustomerInvoiceMapper.INSTANCE.updateCustomerInvoiceMapper(request, existingCustomerInvoice);
        CustomerInvoice updatedCustomerInvoice = customerInvoiceRepository.save(customerInvoice);

        return new UpdateCustomerInvoiceResponse(updatedCustomerInvoice.getId(), updatedCustomerInvoice.getAccountName(), updatedCustomerInvoice.getAccountStatus(), updatedCustomerInvoice.getAccountType(), updatedCustomerInvoice.getCustomer().getId());
    }

    @Override
    public void deleteCustomerInvoice(int id) {

        boolean customerActiveProduct = orderClient.hasActiveProducts(id);

        if (customerActiveProduct) {
            throw new BaseBusinessException("There are product/products connected to the billing account");
        }
        customerInvoiceRepository.deleteById(id);
    }

    @Override
    public List<GetAllCustomerInvoiceResponse> getAllCustomerInvoice() {
        List<CustomerInvoice> customerInvoices = customerInvoiceRepository.findAll();

        return CustomerInvoiceMapper.INSTANCE.customerInvoiceToListCustomerInvoiceResponses(customerInvoices);

    }

    @Override
    public Optional<GetCustomerInvoiceByIdResponse> getByCustomerInvoiceId(int id) {

        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer customerInvoiceId) {

        return customerInvoiceRepository.existsById(customerInvoiceId);
    }

    @Override
    public List<CustomerInvoiceWithCustomerResponse> findByCustomerId(Integer customerId) {

        List<CustomerInvoice> customerInvoices = customerInvoiceRepository.findByCustomerId(customerId);

        return CustomerInvoiceMapper.INSTANCE.customerInvoiceWithCustomer(customerInvoices);
    }

    @Override
    public Optional<GetCustomerInvoiceByIdResponse> findByIdCustomerInvoice(Integer customerInvoiceId) {

        Optional<CustomerInvoice> customerInvoiceOptional = customerInvoiceRepository.findById(customerInvoiceId);

        return customerInvoiceOptional.map(CustomerInvoiceMapper.INSTANCE::getByIdCustomerInvoiceMapper);
    }

    @Override
    public CustomerWithCustomerInvoiceResponse getCustomerByOrderId(Integer invoiceId) {

        Optional<CustomerInvoice> customerInvoiceOptional = customerInvoiceRepository.findById(invoiceId);

        if (customerInvoiceOptional.isPresent()) {
            CustomerInvoice customerInvoice = customerInvoiceOptional.get();
            Customer customer = customerInvoice.getCustomer();

            return CustomerMapper.INSTANCE.toCustomerWithCustomerInvoiceResponse(customer);
        }
        return null;
    }
}
