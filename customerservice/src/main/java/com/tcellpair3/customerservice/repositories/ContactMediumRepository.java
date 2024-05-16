package com.tcellpair3.customerservice.repositories;

import com.tcellpair3.customerservice.entities.ContactMedium;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactMediumRepository extends JpaRepository<ContactMedium,Integer> {
}
