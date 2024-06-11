package com.tcellpair3.customerservice.service.concretes;

import com.tcellpair3.customerservice.clients.CartClient;
import com.tcellpair3.customerservice.core.dtos.responses.customer.GetAllCustomersResponse;
import com.tcellpair3.customerservice.core.mappers.CustomerMapper;
import com.tcellpair3.customerservice.core.service.abstracts.ContactMediumValidationService;
import com.tcellpair3.customerservice.core.service.concretes.CustomerValidationServiceImpl;
import com.tcellpair3.customerservice.entities.ContactMedium;
import com.tcellpair3.customerservice.entities.Customer;
import com.tcellpair3.customerservice.entities.CustomerInvoice;
import com.tcellpair3.customerservice.entities.Gender;
import com.tcellpair3.customerservice.repositories.CustomerInvoiceRepository;
import com.tcellpair3.customerservice.repositories.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

class CustomerServiceImplTest {

    @InjectMocks // sınıfın bir örneğini oluşturup ona mock nesneleri enjecte eder
    private CustomerServiceImpl customerService;

    @Mock // mock nesneleri
    private  CustomerRepository customerRepository;

    @Mock
    private  CustomerValidationServiceImpl customerValidationService;
    @Mock
    private  ContactMediumValidationService contactMediumValidationService;
    @Mock
    private  CustomerInvoiceRepository customerInvoiceRepository;
    @Mock
    private  CartClient cartClient;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenGetAllCustomers_thenReturnCustomerList() {
        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setBirthdate(LocalDate.of(2001, 01, 30));
        customer1.setGender(Gender.FEMALE);
        customer1.setFirstName("Duygu");
        customer1.setLastName("Orhan");
        customer1.setNationalId("26975604548");
        customer1.setContactMedium(new ContactMedium());
        customer1.setCustomerInvoice(Arrays.asList(new CustomerInvoice()));
        customer1.setMiddleName("");
        customer1.setMotherName("Fatma");
        customer1.setFatherName("Serkan");

        Customer customer2 = new Customer();
        customer1.setId(2);
        customer1.setBirthdate(LocalDate.of(2001, 01, 30));
        customer1.setGender(Gender.FEMALE);
        customer1.setFirstName("Duygu");
        customer1.setLastName("Orhan");
        customer1.setNationalId("26975604548");
        customer1.setContactMedium(new ContactMedium());
        customer1.setCustomerInvoice(Arrays.asList(new CustomerInvoice()));
        customer1.setMiddleName("");
        customer1.setMotherName("Fatma");
        customer1.setFatherName("Serkan");

        List<Customer> customers = Arrays.asList(customer2, customer1);
        List<GetAllCustomersResponse> customersResponses = CustomerMapper.INSTANCE.customersToListCustomerResponses(customers);

        //Mock davranışlarını çağırma
        when(customerRepository.findAll()).thenReturn(customers);

        //test edilen metodu çağırma
        List<GetAllCustomersResponse> result = customerService.getAllCustomers();

        // sonuçları doğrulama
        assertEquals(customersResponses.size(), result.size());

        // Detaylı doğrulama
        assertAll("customer1",
                () -> assertEquals(customersResponses.get(0).getFirstName(), result.get(0).getFirstName()),
                () -> assertEquals(customersResponses.get(0).getLastName(), result.get(0).getLastName()),
                () -> assertEquals(customersResponses.get(0).getMiddleName(), result.get(0).getMiddleName()),
                () -> assertEquals(customersResponses.get(0).getNationalId(), result.get(0).getNationalId()),
                () -> assertEquals(customersResponses.get(0).getMotherName(), result.get(0).getMotherName()),
                () -> assertEquals(customersResponses.get(0).getFatherName(), result.get(0).getFatherName()),
                () -> assertEquals(customersResponses.get(0).getBirthdate(), result.get(0).getBirthdate()),
                () -> assertEquals(customersResponses.get(0).getGender(), result.get(0).getGender())
                // () -> assertEquals(customersResponses.get(0).getContactMedium(), result.get(0).getContactMedium()),
                //() -> assertEquals(customersResponses.get(0).getCustomerInvoices().size(), result.get(0).getCustomerInvoices().size())
        );

        assertAll("customer2",
                () -> assertEquals(customersResponses.get(1).getFirstName(), result.get(1).getFirstName()),
                () -> assertEquals(customersResponses.get(1).getLastName(), result.get(1).getLastName()),
                () -> assertEquals(customersResponses.get(1).getMiddleName(), result.get(1).getMiddleName()),
                () -> assertEquals(customersResponses.get(1).getNationalId(), result.get(1).getNationalId()),
                () -> assertEquals(customersResponses.get(1).getMotherName(), result.get(1).getMotherName()),
                () -> assertEquals(customersResponses.get(1).getFatherName(), result.get(1).getFatherName()),
                () -> assertEquals(customersResponses.get(1).getBirthdate(), result.get(1).getBirthdate()),
                () -> assertEquals(customersResponses.get(1).getGender(), result.get(1).getGender())
                // () -> assertEquals(customersResponses.get(1).getContactMedium(), result.get(1).getContactMedium()),
                // () -> assertEquals(customersResponses.get(1).getCustomerInvoices().size(), result.get(1).getCustomerInvoices().size())
        );
    }

    @AfterEach
    void tearDown(){
            reset(customerRepository);
    }

}