package com.tcellpair3.customerservice.repositories;

import com.tcellpair3.customerservice.entities.CustomerInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerInvoiceRepository extends JpaRepository<CustomerInvoice,Integer> {
    List<CustomerInvoice> findByCustomerId(Integer customerId);

    @Query("SELECT ci FROM CustomerInvoice ci " +
            "JOIN ci.customer c " +
            "JOIN c.addresses a " +
            "WHERE ci.id = :invoiceId")
    CustomerInvoice findCustomerInvoiceWithAddressesById(@Param("invoiceId") Integer invoiceId);

}
