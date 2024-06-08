package com.tcellpair3.cartservice.controller;

import com.tcellpair3.cartservice.entities.Cart;
import com.tcellpair3.cartservice.service.abstracts.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;


    @GetMapping("/{id}")
    public Optional<Cart> getByIdCart(@PathVariable("id") String cartId) {
        return cartService.getByIdCart(cartId);
    }
    @GetMapping()
    public List<Cart> findAll()
    {
        return cartService.getAllCarts();
    }
    @GetMapping("/totalPrice/{customerInvoiceId}")
    public double getTotalPriceByCustomerInvoiceId(@PathVariable Integer customerInvoiceId) {
        return cartService.getTotalPriceByCustomerInvoiceId(customerInvoiceId);
    }
    @GetMapping("/customerInvoice/{customerInvoiceId}")
    public ResponseEntity<List<Cart>> getCartsByCustomerInvoiceId(@PathVariable("customerInvoiceId") int customerInvoiceId) {
        List<Cart> carts = cartService.getCartsByCustomerInvoiceId(customerInvoiceId);
        if (carts.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(carts);
        }
    }
    @GetMapping("/customer/{customerInvoiceId}/active-products")
    public boolean hasActiveProducts(@PathVariable("customerInvoiceId") int customerInvoiceId) {
        return cartService.hasActiveProducts(customerInvoiceId);
    }
    @PostMapping("/addProductToCustomer/{customerInvoiceId}/{productId}/{addressId}")
    public ResponseEntity<String> addProductToCustomer(
            @PathVariable("customerInvoiceId") Integer customerInvoiceId,
            @PathVariable("productId") Integer productId,
            @PathVariable("addressId") Integer addressId) {
        cartService.addProductToCustomer(customerInvoiceId, productId,addressId);
        return ResponseEntity.ok("Product added to customer successfully");
    }
    @DeleteMapping("{id}")
    public void cartDelete(@PathVariable String id)
    {
        cartService.deleteCart(id);
    }
}