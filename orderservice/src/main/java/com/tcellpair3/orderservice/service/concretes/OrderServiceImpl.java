package com.tcellpair3.orderservice.service.concretes;

import com.tcellpair3.orderservice.clients.CartClient;
import com.tcellpair3.orderservice.core.dtos.response.CartResponse;
import com.tcellpair3.orderservice.entities.Order;
import com.tcellpair3.orderservice.repository.OrderRepository;
import com.tcellpair3.orderservice.service.abstracts.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartClient cartClient;
    @Override
    public List<CartResponse> getCartsByCustomerInvoiceId(int customerInvoiceId) {
        ResponseEntity<List<CartResponse>> response = cartClient.getCartsByCustomerInvoiceId(customerInvoiceId);
        if(response.getStatusCode().is2xxSuccessful()){
            return  response.getBody();
        } else{
            throw new RuntimeException("Failed to fetch baskets from cart service");
        }
    }

    @Override
    public void createOrder(CartResponse cartResponse) {
        List<Order> existingOrders =orderRepository.findByCustomerInvoiceId(cartResponse.getCustomerInvoiceId());
        if (!existingOrders.isEmpty()) {
            // Mevcut sipariş varsa ürünleri ve fiyatı güncelle
            Order existingOrder = existingOrders.get(0);
            existingOrder.getOrderItems().add(cartResponse.getProductName() + " (" + cartResponse.getProductNo() + ")");
            existingOrder.setTotalAmount(existingOrder.getTotalAmount() + cartResponse.getPrice());
            orderRepository.save(existingOrder);
        } else {
            // Mevcut sipariş yoksa yeni sipariş oluştur
            Order order = new Order();
            order.setServiceAddresses(Arrays.asList(
                    cartResponse.getCity(),
                    cartResponse.getDistrict(),
                    cartResponse.getStreet(),
                    cartResponse.getHouseFlatNumber(),
                    cartResponse.getAddressDescription()
            ));
            order.setOrderItems(new ArrayList<>(List.of(
                    cartResponse.getProductName() + " (" + cartResponse.getProductNo() + ")"
            )));
            order.setTotalAmount(cartResponse.getPrice());
            order.setCustomerInvoiceId(cartResponse.getCustomerInvoiceId());
            orderRepository.save(order);
        }
    }

    @Override
    public List<Order> getOrdersByCustomerInvoiceId(int customerInvoiceId) {
        return orderRepository.findByCustomerInvoiceId((customerInvoiceId));
    }

    @Override
    public Optional<Order> getByOrderId(int orderId) {
        return orderRepository.findById(orderId);
    }
}
