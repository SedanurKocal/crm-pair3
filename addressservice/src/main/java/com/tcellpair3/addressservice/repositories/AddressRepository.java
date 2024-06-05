package com.tcellpair3.addressservice.repositories;

import com.tcellpair3.addressservice.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Integer> {
    List<Address> findByCustomerId(Integer customerId);
    List<Address> findAddressesByCustomerId(Integer customerId);
    int countByCustomerId(Integer customerId);
    boolean existsById(Integer id);
}
