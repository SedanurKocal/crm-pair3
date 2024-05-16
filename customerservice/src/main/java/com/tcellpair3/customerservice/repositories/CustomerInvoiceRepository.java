package com.tcellpair3.customerservice.repositories;

import com.tcellpair3.customerservice.entities.CustomerInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerInvoiceRepository extends JpaRepository<CustomerInvoice,Integer> {
}
