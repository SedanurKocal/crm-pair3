package com.tcellpair3.customerservice.repositories;

import com.tcellpair3.customerservice.entities.CustomerInvoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CustomerInvoiceRepository extends JpaRepository<CustomerInvoice,Integer> {

    List<CustomerInvoice> findByCustomerId(Integer customerId);

    boolean existsById(Integer id);

    Page<CustomerInvoice> findByCustomerId(Integer customerId, Pageable pageable);
}
