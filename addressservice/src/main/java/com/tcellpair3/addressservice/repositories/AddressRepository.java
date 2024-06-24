package com.tcellpair3.addressservice.repositories;

import com.tcellpair3.addressservice.core.dto.responses.AddressResponse;
import com.tcellpair3.addressservice.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address,Integer> {
    List<Address> findByCustomerId(Integer customerId);
    List<AddressResponse> findAddressesByCustomerId(Integer customerId);
    int countByCustomerId(Integer customerId);
    boolean existsById(Integer id);
    boolean existsByCustomerId(int customerId);

}
