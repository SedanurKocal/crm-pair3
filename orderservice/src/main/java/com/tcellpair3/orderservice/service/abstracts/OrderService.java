package com.tcellpair3.orderservice.service.abstracts;

import com.tcellpair3.orderservice.core.dtos.response.CartResponse;
import com.tcellpair3.orderservice.entities.Order;

import java.util.List;
import java.util.Optional;
public interface OrderService {

    List<CartResponse> getCartsByCustomerInvoiceId(int customerInvoiceId);
    void createOrder(CartResponse cartResponse);
    List<Order> getOrdersByCustomerInvoiceId(int customerInvoiceId);
    boolean hasActiveProducts(int customerInvoiceId);
    Optional<Order> getByOrderId(int orderId);
}
