package com.tcellpair3.customerservice.service.concretes;

import com.tcellpair3.customerservice.clients.CartClient;
import com.tcellpair3.customerservice.core.dtos.requests.customer.CreateCustomerRequest;
import com.tcellpair3.customerservice.core.dtos.requests.customer.UpdateCustomerRequest;
import com.tcellpair3.customerservice.core.dtos.responses.customer.*;
import com.tcellpair3.customerservice.core.exception.type.BusinessException;
import com.tcellpair3.customerservice.core.exception.type.IllegalArgumentException;
import com.tcellpair3.customerservice.core.mappers.CustomerMapper;
import com.tcellpair3.customerservice.core.mernis.IRKKPSPublicSoap;
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
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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

    @Mock
    private IRKKPSPublicSoap client;

    private Customer customer1;
    private Customer customer2;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);

        customer1 = new Customer();
        customer1.setId(1);
        customer1.setBirthdate(LocalDate.of(2001, 01, 30));
        customer1.setGender(Gender.FEMALE);
        customer1.setFirstName("Duygu");
        customer1.setLastName("Orhan");
        customer1.setAccountNumber(12345);
        customer1.setNationalId("26975604548");
        customer1.setContactMedium(new ContactMedium());
        customer1.setCustomerInvoice(Arrays.asList(new CustomerInvoice()));
        customer1.setMiddleName("");
        customer1.setMotherName("Fatma");
        customer1.setFatherName("Serkan");

        customer2 = new Customer();
        customer1.setId(2);
        customer1.setBirthdate(LocalDate.of(2001, 01, 30));
        customer1.setGender(Gender.FEMALE);
        customer1.setFirstName("Duygu");
        customer1.setLastName("Orhan");
        customer1.setAccountNumber(12345);
        customer1.setNationalId("26975604548");
        customer1.setContactMedium(new ContactMedium());
        customer1.setCustomerInvoice(Arrays.asList(new CustomerInvoice()));
        customer1.setMiddleName("");
        customer1.setMotherName("Fatma");
        customer1.setFatherName("Serkan");
    }

    @Test
    void whenGetAllCustomers_thenReturnCustomerList() {
        //Arrange


        List<Customer> customers = Arrays.asList(customer2, customer1);
        List<GetAllCustomersResponse> customersResponses = CustomerMapper.INSTANCE.customersToListCustomerResponses(customers);

        //Mock davranışlarını çağırma
        when(customerRepository.findAll()).thenReturn(customers);

        //test edilen metodu çağırma
        //Act
        List<GetAllCustomersResponse> result = customerService.getAllCustomers();

        // sonuçları doğrulama
        //Assert
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

    @Test
    void createCustomer_whenValidRequest_thenReturnCreateCustomerResponse() throws Exception {
        // Test verilerinin hazırlanması Arrange
        CreateCustomerRequest request = new CreateCustomerRequest();
        request.setAccountNumber(123456);
        request.setFirstName("Duygu");
        request.setLastName("Orhan");
        request.setMiddleName("");
        request.setNationalId("26975604548");
        request.setMotherName("Fatma");
        request.setFatherName("Serkan");
        request.setBirthdate(LocalDate.of(2001, 1, 1));
        request.setGender(Gender.FEMALE);

        Customer customer = new Customer();
        customer.setId(1); // ID'yi Integer olarak ayarlayın
        customer.setAccountNumber(123456);
        customer.setFirstName("Duygu");
        customer.setLastName("Orhan");
        customer.setMiddleName("");
        customer.setNationalId("26975604548");
        customer.setMotherName("Fatma");
        customer.setFatherName("Serkan");
        customer.setBirthdate(LocalDate.of(2001, 1, 1));
        customer.setGender(Gender.FEMALE);

        //IRKKPSPublicSoap client = mock(IRKKPSPublicSoap.class); // Mock obje oluşturun

        // Bağımlı servislerin davranışlarının belirlenmesi -- Act
        when(client.TCKimlikNoDogrula(Long.parseLong("26975604548"), "Duygu", "Orhan", 2001)).thenReturn(true);
        when(customerRepository.existsByNationalId("26975604548")).thenReturn(false);
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        // Test edilecek metodun çalıştırılması
        CreateCustomerResponse response = customerService.createCustomer(request);
        //Assert
        // Sonuçların karşılaştırılması
        assertEquals(1, response.getId());
        assertEquals(123456, response.getAccountNumber());
        assertEquals("Duygu", response.getFirstName());
        assertEquals("Orhan", response.getLastName());
        assertEquals("", response.getMiddleName());
        assertEquals("26975604548", response.getNationalId());
        assertEquals("Fatma", response.getMotherName());
        assertEquals("Serkan", response.getFatherName());
        assertEquals(LocalDate.of(2001, 1, 1), response.getBirthdate());
        assertEquals(Gender.FEMALE, response.getGender());

        // Bağımlı servislerin çağrıldığının doğrulanması
        verify(customerRepository).existsByNationalId("26975604548");
        verify(customerRepository).save(any(Customer.class)); // save metodunun çağrıldığını doğrulayın
    }


    @Test
    void createCustomer_whenCustomerExistsWithSameNationalId_thenThrowBusinessException() throws Exception {
        CreateCustomerRequest request = new CreateCustomerRequest();
        request.setAccountNumber(123456);
        request.setFirstName("Duygu");
        request.setLastName("Orhan");
        request.setMiddleName("");
        request.setNationalId("26975604548");
        request.setMotherName("Fatma");
        request.setFatherName("Serkan");
        request.setBirthdate(LocalDate.of(2001, 1, 1));
        request.setGender(Gender.FEMALE);

        //IRKKPSPublicSoap client = mock(IRKKPSPublicSoap.class); // Mock obje oluşturun

        when(client.TCKimlikNoDogrula(Long.parseLong("26975604548"), "Duygu", "Orhan", 2001)).thenReturn(true);
        when(customerRepository.existsByNationalId(request.getNationalId())).thenReturn(true);

        assertThrows(BusinessException.class,()->{
            customerService.createCustomer(request);
        });


    }

    @Test
    void createCustomer_whenUserNotFound_thenThrowIllegalArgumentException() throws Exception {
        CreateCustomerRequest request = new CreateCustomerRequest();
        request.setAccountNumber(123456);
        request.setFirstName("Orhan");
        request.setLastName("Orhan");
        request.setMiddleName("");
        request.setNationalId("26975604999");
        request.setMotherName("Fatma");
        request.setFatherName("Serkan");
        request.setBirthdate(LocalDate.of(2001, 1, 1));
        request.setGender(Gender.FEMALE);

        //IRKKPSPublicSoap client = mock(IRKKPSPublicSoap.class); // Mock obje oluşturun
        //Act
        when(client.TCKimlikNoDogrula(Long.parseLong("26975604999"), "Orhan", "Orhan", 2001)).thenReturn(false);
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            customerService.createCustomer(request);
        });
    }

    @Test
    void deleteCustomer_whenCustomerHasActiveProducts_thenThrowBusinessException(){
        //Arrange
        int customerId=1;
        when(cartClient.hasActiveProducts(customerId)).thenReturn(true);
        //Act

        BusinessException exception = assertThrows(BusinessException.class, () -> customerService.deleteCustomer(customerId));
        assertEquals("Since the customer has active products, the customer cannot be deleted.", exception.getMessage());

        verify(cartClient).hasActiveProducts(customerId);
        verify(customerRepository, never()).deleteById(customerId);
    }

    @Test
    void deleteCustomer_whenCustomerDoesNotHaveActiveProducts_thenSuccess() {
        int customerId = 1;
        when(cartClient.hasActiveProducts(customerId)).thenReturn(false);

        assertDoesNotThrow(() -> customerService.deleteCustomer(customerId));

        verify(cartClient).hasActiveProducts(customerId);
        verify(customerRepository).deleteById(customerId);
    }

    @Test
    void updateCustomer_whenNationalIdExists_thenThrowBusinessException() throws Exception {
        // Arrange
        int customerId = 1;

        UpdateCustomerRequest request = new UpdateCustomerRequest();
        request.setAccountNumber(123456);
        request.setFirstName("Duygu");
        request.setLastName("Orhan");
        request.setMiddleName("");
        request.setNationalId("26975604548");
        request.setMotherName("Fatma");
        request.setFatherName("Serkan");
        request.setBirthdate(LocalDate.of(2001, 1, 1));
        request.setGender(Gender.FEMALE);

        when(client.TCKimlikNoDogrula(
                Long.parseLong(request.getNationalId()),
                request.getFirstName(),
                request.getLastName(),
                request.getBirthdate().getYear()))
                .thenReturn(true);
        when(customerRepository.existsByNationalId(request.getNationalId())).thenReturn(true);

        // Act & Assert
        BusinessException exception = assertThrows(BusinessException.class, () -> customerService.updateCustomer(customerId, request));
        assertEquals("A customer is already exist with this Nationality ID", exception.getMessage());

        // Verify
        verify(customerRepository).existsByNationalId(request.getNationalId());
        verify(customerRepository, never()).findById(customerId);
    }



    @Test
    void updateCustomer_whenNotRealPerson_thenThrowBusinessException() throws Exception {
        // Arrange
        int customerId = 1;

        UpdateCustomerRequest request = new UpdateCustomerRequest();
        request.setAccountNumber(123456);
        request.setFirstName("Duygu");
        request.setLastName("Orhan");
        request.setMiddleName("");
        request.setNationalId("26975604549"); //yanlış National id
        request.setMotherName("Fatma");
        request.setFatherName("Serkan");
        request.setBirthdate(LocalDate.of(2001, 1, 1));
        request.setGender(Gender.FEMALE);

        when(client.TCKimlikNoDogrula(
                Long.parseLong(request.getNationalId()),
                request.getFirstName(),
                request.getLastName(),
                request.getBirthdate().getYear()))
                .thenReturn(false);

        // Act & Assert
        BusinessException exception = assertThrows(BusinessException.class, () -> customerService.updateCustomer(customerId, request));
        assertEquals("Kullanıcı bulunamadı", exception.getMessage());

        // Verify
        verify(customerRepository, never()).existsByNationalId(request.getNationalId());
        verify(customerRepository, never()).findById(customerId);

    }

    @Test
    void updateCustomer_whenCustomerNotFound_thenThrowBusinessException() throws Exception {
        // Arrange
        int customerId = 1;

        UpdateCustomerRequest request = new UpdateCustomerRequest();
        request.setAccountNumber(123456);
        request.setFirstName("Duygu");
        request.setLastName("Orhan");
        request.setMiddleName("");
        request.setNationalId("26975604548");
        request.setMotherName("Fatma");
        request.setFatherName("Serkan");
        request.setBirthdate(LocalDate.of(2001, 1, 1));
        request.setGender(Gender.FEMALE);

        when(client.TCKimlikNoDogrula(
                Long.parseLong(request.getNationalId()),
                request.getFirstName(),
                request.getLastName(),
                request.getBirthdate().getYear()))
                .thenReturn(true);
        when(customerRepository.existsByNationalId(request.getNationalId())).thenReturn(false);
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        // Act & Assert
        BusinessException exception = assertThrows(BusinessException.class, () -> customerService.updateCustomer(customerId, request));
        assertEquals("Kullanıcı Bulunamadı", exception.getMessage());

        // Verify
        verify(customerRepository).existsByNationalId(request.getNationalId());
        verify(customerRepository).findById(customerId);
    }

    @Test
    void updateCustomer_whenValidRequest_thenUpdateCustomerSuccessfully() throws Exception {
        // Arrange
        int customerId = 1;

        UpdateCustomerRequest request = new UpdateCustomerRequest();
        request.setAccountNumber(123456);
        request.setFirstName("Duygu");
        request.setLastName("Orhan");
        request.setMiddleName("");
        request.setNationalId("26975604548");
        request.setMotherName("Fatma");
        request.setFatherName("Serkan");
        request.setBirthdate(LocalDate.of(2001, 1, 1));
        request.setGender(Gender.FEMALE);

        Customer existingCustomer = new Customer();
        existingCustomer.setId(customerId);
        existingCustomer.setNationalId("26975604548");

        when(client.TCKimlikNoDogrula(
                Long.parseLong(request.getNationalId()),
                request.getFirstName(),
                request.getLastName(),
                request.getBirthdate().getYear()))
                .thenReturn(true);
        when(customerRepository.existsByNationalId(request.getNationalId())).thenReturn(false);
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(existingCustomer));
        when(customerRepository.save(any(Customer.class))).thenReturn(existingCustomer);

        // Act
        UpdateCustomerResponse response = customerService.updateCustomer(customerId, request);

        // Assert
        assertNotNull(response);
        assertEquals(customerId, response.getId());
        assertEquals(request.getAccountNumber(), response.getAccountNumber());
        assertEquals(request.getFirstName(), response.getFirstName());
        assertEquals(request.getLastName(), response.getLastName());
        assertEquals(request.getNationalId(), response.getNationalId());

        // Verify
        verify(customerRepository).existsByNationalId(request.getNationalId());
        verify(customerRepository).findById(customerId);
        verify(customerRepository).save(any(Customer.class));
    }


    @Test
    void getByCustomerId_whenCustomerExists_thenReturnCustomer() {
        // Arrange
        int customerId = 1;

        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setAccountNumber(123456);
        customer.setFirstName("Duygu");
        customer.setLastName("Orhan");
        customer.setMiddleName("");
        customer.setNationalId("26975604548");
        customer.setMotherName("Fatma");
        customer.setFatherName("Serkan");
        customer.setBirthdate(LocalDate.of(2001, 1, 1));
        customer.setGender(Gender.FEMALE);

        GetCustomerByIdResponse expectedResponse = new GetCustomerByIdResponse();
        expectedResponse.setFirstName("Duygu");
        expectedResponse.setLastName("Orhan");
        expectedResponse.setAccountNumber(123456);
        expectedResponse.setMiddleName("");
        expectedResponse.setNationalId("26975604548");
        expectedResponse.setMotherName("Fatma");
        expectedResponse.setFatherName("Serkan");
        expectedResponse.setBirthdate(LocalDate.of(2001, 1, 1));
        expectedResponse.setGender(Gender.FEMALE);


        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        // Act
        Optional<GetCustomerByIdResponse> actualResponse = customerService.getByCustomerId(customerId);

        // Assert
        assertEquals(expectedResponse.getAccountNumber(), actualResponse.get().getAccountNumber());
        assertEquals(expectedResponse.getFirstName(), actualResponse.get().getFirstName());
        assertEquals(expectedResponse.getLastName(), actualResponse.get().getLastName());
        assertEquals(expectedResponse.getMiddleName(), actualResponse.get().getMiddleName());
        assertEquals(expectedResponse.getNationalId(), actualResponse.get().getNationalId());
        assertEquals(expectedResponse.getMotherName(), actualResponse.get().getMotherName());
        assertEquals(expectedResponse.getFatherName(), actualResponse.get().getFatherName());
        assertEquals(expectedResponse.getBirthdate(), actualResponse.get().getBirthdate());
        assertEquals(expectedResponse.getGender(), actualResponse.get().getGender());


        // Verify
        verify(customerRepository).findById(customerId);
    }

    @Test
    void getByCustomerId_whenCustomerDoesNotExist_thenReturnEmpty() {
        // Arrange
        int customerId = 1;

        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        // Act
        Optional<GetCustomerByIdResponse> actualResponse = customerService.getByCustomerId(customerId);

        // Assert
        assertFalse(actualResponse.isPresent());

        // Verify
        verify(customerRepository).findById(customerId);
    }
    @Test
    void givenCustomerFirstName_whenGetCustomersByFirstName_thenReturnCustomerPage() {

        String firstname="Du";
        int page=0;
        int size=10;
        Pageable pageable=PageRequest.of(page,size);
        List<Customer> customers = Arrays.asList(customer1,customer2);
        Page<Customer> customerPage = new PageImpl<>(customers);

        //Act
        when(customerRepository.findByFirstNameStartingWithIgnoreCase(firstname,pageable)).thenReturn(customerPage);
        Page<SearchResultsResponse> responses = customerService.getCustomersByFirstName(firstname,page,size);

        //Then
        assertNotNull(responses);
        assertEquals(2,responses.getTotalElements());
        verify(customerRepository,times(1)).findByFirstNameStartingWithIgnoreCase(firstname,pageable);

    }
    @Test
    void givenCustomerFirstName_whenFindByFirstName_thenReturnCustomerList() {
        // Arrange
        String firstName = "Duygu";
        List<Customer> customers = Arrays.asList(customer1);

        // Act
        when(customerRepository.findByFirstName(firstName)).thenReturn(customers);


        List<SearchResultsResponse> result = customerService.findByFirstName(firstName);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(customerRepository, times(1)).findByFirstName(firstName);
    }

    @Test
    void givenCustomerFirstName_whenFindByFirstName_thenThrowBusinessException() {
        // Arrange
        String firstName = "NonExisting";
        when(customerRepository.findByFirstName(firstName)).thenReturn(Collections.emptyList());

        // Act
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            customerService.findByFirstName(firstName);
        });

        //Assert
        assertEquals("No customer found! Would you like to create the customer?", exception.getMessage());
        verify(customerRepository, times(1)).findByFirstName(firstName);
    }


    @Test
    void givenCustomerLastName_whenFindByLastName_thenReturnCustomerList() {
        // Arrange
        String lastName = "Doe";
        List<Customer> customers = Arrays.asList(customer1, customer2);

        when(customerRepository.findByLastName(lastName)).thenReturn(customers);

        // Act
        List<SearchResultsResponse> result = customerService.findByLastName(lastName);

        // Asserts
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(customerRepository, times(1)).findByLastName(lastName);
    }

    @Test
    void givenCustomerLastName_whenFindByLastName_thenThrowBusinessException() {
        // Arange
        String lastName = "NonExisting";
        when(customerRepository.findByLastName(lastName)).thenReturn(Collections.emptyList());

        // Act
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            customerService.findByLastName(lastName);
        });

        //Assert
        assertEquals("No customer found! Would you like to create the customer?", exception.getMessage());
        verify(customerRepository, times(1)).findByLastName(lastName);
    }
    @Test
    void givenAccountNumber_whenFindByAccountNumber_thenReturnCustomerList() {
        // Arrange
        Integer accountNumber = 12345;
        List<Customer> customers = Arrays.asList(customer1, customer2);

        when(customerRepository.findByAccountNumber(accountNumber)).thenReturn(customers);

        // Act
        List<SearchResultsResponse> result = customerService.findByAccountNumber(accountNumber);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(customerRepository, times(1)).findByAccountNumber(accountNumber);
    }

    @Test
    void givenAccountNumber_whenFindByAccountNumber_thenThrowBusinessException() {
        // Arrange
        Integer accountNumber = 67890;
        when(customerRepository.findByAccountNumber(accountNumber)).thenReturn(Collections.emptyList());

        // Act
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            customerService.findByAccountNumber(accountNumber);
        });

        //Assert
        assertEquals("No customer found! Would you like to create the customer?", exception.getMessage());
        verify(customerRepository, times(1)).findByAccountNumber(accountNumber);
    }

    @Test
    void givenNationalId_whenFindByNationalId_thenReturnCustomerList() {
        // Arrange
        String nationalId = "26975604548";
        List<Customer> customers = Arrays.asList(customer1, customer2);

        when(customerRepository.findByNationalId(nationalId)).thenReturn(customers);

        // Act
        List<SearchResultsResponse> result = customerService.findByNationalId(nationalId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(customerRepository, times(1)).findByNationalId(nationalId);
    }

    @Test
    void givenNationalId_whenFindByNationalId_thenThrowBusinessException() {
        // Arrange
        String nationalId = "987654321";
        when(customerRepository.findByNationalId(nationalId)).thenReturn(Collections.emptyList());

        // act
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            customerService.findByNationalId(nationalId);
        });

        assertEquals("No customer found! Would you like to create the customer?", exception.getMessage());
        verify(customerRepository, times(1)).findByNationalId(nationalId);
    }


    @Test
    void givenMobilePhone_whenFindByContactMedium_MobilePhone_thenReturnCustomerList() {

        String mobilePhone = "1234567890";
        List<Customer> customers = Arrays.asList(customer1, customer2);

        doNothing().when(contactMediumValidationService).validatePhoneNumber(mobilePhone);
        when(customerRepository.findByContactMedium_MobilePhone(mobilePhone)).thenReturn(customers);


        List<SearchResultsResponse> result = customerService.findByContactMedium_MobilePhone(mobilePhone);


        assertNotNull(result);
        assertEquals(2, result.size());
        verify(contactMediumValidationService, times(1)).validatePhoneNumber(mobilePhone);
        verify(customerRepository, times(1)).findByContactMedium_MobilePhone(mobilePhone);
    }


    @Test
    void givenMobilePhone_whenFindByContactMedium_MobilePhone_thenThrowBusinessException() {

        String mobilePhone = "0987654321";

        doNothing().when(contactMediumValidationService).validatePhoneNumber(mobilePhone);
        when(customerRepository.findByContactMedium_MobilePhone(mobilePhone)).thenReturn(Collections.emptyList());


        BusinessException exception = assertThrows(BusinessException.class, () -> {
            customerService.findByContactMedium_MobilePhone(mobilePhone);
        });

        assertEquals("No customer found! Would you like to create the customer?", exception.getMessage());
        verify(contactMediumValidationService, times(1)).validatePhoneNumber(mobilePhone);
        verify(customerRepository, times(1)).findByContactMedium_MobilePhone(mobilePhone);
    }

    @Test
    void givenCustomerId_whenExistsById_thenReturnTrue() {
        // Arrange ----> Burada metot doğru bir şekilde çağrılıyor mu onu kontrol ediyoruz
        Integer customerId = 1;

        when(customerRepository.existsById(customerId)).thenReturn(true);

        // Act
        boolean result = customerService.existsById(customerId);

        // Assert
        assertTrue(result);
        verify(customerRepository, times(1)).existsById(customerId);
    }

    @Test
    void givenCustomerId_whenExistsById_thenReturnFalse() {
        // Arrange
        Integer customerId = 1;

        when(customerRepository.existsById(customerId)).thenReturn(false);

        // Act
        boolean result = customerService.existsById(customerId);

        // Assert
        assertFalse(result);
        verify(customerRepository, times(1)).existsById(customerId);
    }

    @Test
    void givenCartId_whenCartNumber_thenCallGetByIdCart() {
        // Given
        int cartId = 123;

        // When
        customerService.cartNumber(cartId);

        // Then
        verify(cartClient, times(1)).getByIdCart(cartId);
    }



        @AfterEach
    void tearDown(){
            reset(customerRepository);
    }

}