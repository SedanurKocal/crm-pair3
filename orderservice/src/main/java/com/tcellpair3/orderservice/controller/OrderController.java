package com.tcellpair3.orderservice.controller;

import com.tcellpair3.orderservice.core.dtos.response.CartResponse;
import com.tcellpair3.orderservice.entities.Order;
import com.tcellpair3.orderservice.service.abstracts.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/customerInvoice/{customerInvoiceId}/baskets")
    public ResponseEntity<List<CartResponse>> getBasketsByCustomerInvoiceId(@PathVariable("customerInvoiceId") int customerInvoiceId) {
        List<CartResponse> baskets = orderService.getCartsByCustomerInvoiceId(customerInvoiceId);
        return ResponseEntity.ok(baskets);
    }

    @PostMapping("/save")
    public ResponseEntity<String> createOrder(@RequestBody CartResponse cart) {
        orderService.createOrder(cart);
        return ResponseEntity.ok("Order saved successfully");
    }

    @GetMapping("/{orderId}")
    public Optional<Order> getByIdOrder(@PathVariable Integer orderId)
    {
        return orderService.getByOrderId(orderId);
    }

    @GetMapping("/getOrdersAndTotalAmountByCustomerInvoiceId/{customerInvoiceId}")
    public Map<String, Object> getOrdersAndTotalAmountByCustomerInvoiceId(@PathVariable int customerInvoiceId) {
        List<Order> orders = orderService.getOrdersByCustomerInvoiceId(customerInvoiceId);
        Map<String, Object> response = new HashMap<>();
        response.put("orders", orders);
        return response;
    }

    @GetMapping("/customer/{customerInvoiceId}/active-products")
    public boolean hasActiveProducts(@PathVariable("customerInvoiceId") int customerInvoiceId) {
        return orderService.hasActiveProducts(customerInvoiceId);
    }
}
