package com.tcellpair3.customerservice.repositories;

import com.tcellpair3.customerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    boolean existsByNationalId(String nationalId);
    List<Customer> findByFirstNameStartingWithIgnoreCase(String nameStart);

}
