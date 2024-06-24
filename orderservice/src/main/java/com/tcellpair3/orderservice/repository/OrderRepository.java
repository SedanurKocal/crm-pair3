package com.tcellpair3.orderservice.repository;

import com.tcellpair3.orderservice.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findByCustomerInvoiceId(int customerInvoiceId);
    boolean existsByCustomerInvoiceId(Integer customerId);
}
