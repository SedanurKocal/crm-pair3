package com.tcellpair3.customerservice.repositories;

import com.tcellpair3.customerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    boolean existsByNationalId(String nationalId);
}
