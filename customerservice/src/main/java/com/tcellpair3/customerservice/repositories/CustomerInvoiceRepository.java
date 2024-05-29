package com.tcellpair3.customerservice.repositories;

import com.tcellpair3.customerservice.core.dtos.responses.customerinvoice.CustomerInvoiceWithCustomerResponse;
import com.tcellpair3.customerservice.entities.CustomerInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerInvoiceRepository extends JpaRepository<CustomerInvoice,Integer> {
    List<CustomerInvoice> findByCustomerId(Integer customerId);




}
