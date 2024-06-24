package com.tcellpair3.cartservice.service.abstracts;

import com.tcellpair3.cartservice.core.dtos.ProductResponse;
import com.tcellpair3.cartservice.entities.Cart;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface CartService {

    void addProductToCustomer(Integer customerInvoceId, Integer productId,Integer addressId);

    void deleteCart(String cartId);

    Optional<Cart> getByIdCart(String cartId);

    List<Cart> getCartsByCustomerInvoiceId(Integer customerInvoiceId);

    double getTotalPriceByCustomerInvoiceId(Integer customerInvoiceId);

    List<Cart> getAllCarts();
}
