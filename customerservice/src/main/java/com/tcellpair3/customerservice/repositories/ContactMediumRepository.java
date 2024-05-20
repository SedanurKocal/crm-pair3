package com.tcellpair3.customerservice.repositories;

import com.tcellpair3.customerservice.core.dtos.responses.contactmedium.GetAllContactMediumResponse;
import com.tcellpair3.customerservice.core.mappers.ContactMediumMapper;
import com.tcellpair3.customerservice.entities.ContactMedium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactMediumRepository extends JpaRepository<ContactMedium,Integer> {

    List<ContactMedium> findByCustomerId(int customerId);

}
