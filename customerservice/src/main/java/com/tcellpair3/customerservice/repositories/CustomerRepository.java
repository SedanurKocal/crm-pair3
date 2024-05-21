package com.tcellpair3.customerservice.repositories;

import com.tcellpair3.customerservice.entities.Address;
import com.tcellpair3.customerservice.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    boolean existsByNationalId(String nationalId);

    Page<Customer> findByFirstNameStartingWithIgnoreCase(String firstName, Pageable pageable);
    List<Customer> findByFirstName(String firstName);
    List<Customer> findByLastName(String lastName);
    List<Customer> findByAccountNumber(Integer accountNumber);
    List<Customer> findByNationalId(String nationalId);
    List<Customer> findByContactMedium_MobilePhone(String mobilePhone);

    @Query("SELECT c.addresses FROM Customer c WHERE c.id = :customerId")
    List<Address> findAddressesByCustomerId(@Param("customerId") Integer customerId);

    //TODO: order search



}
