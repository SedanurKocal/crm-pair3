package com.tcellpair3.cartservice.repository;

import com.tcellpair3.cartservice.entities.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CartRepository extends MongoRepository<Cart, String> {
    boolean existsByCustomerInvoiceId(Integer customerId);
    List<Cart> findByCustomerInvoiceId(Integer customerInvoiceId);
}