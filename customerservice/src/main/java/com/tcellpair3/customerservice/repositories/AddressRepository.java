package com.tcellpair3.customerservice.repositories;

import com.tcellpair3.customerservice.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Integer> {
}
