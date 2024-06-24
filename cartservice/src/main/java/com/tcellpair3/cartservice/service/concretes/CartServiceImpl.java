package com.tcellpair3.cartservice.service.concretes;

import com.tcellpair3.cartservice.client.AddressClient;
import com.tcellpair3.cartservice.client.CustomerInvoiceClient;
import com.tcellpair3.cartservice.client.OrderClient;
import com.tcellpair3.cartservice.client.ProductServiceClient;
import com.tcellpair3.cartservice.core.dtos.AddressResponse;
import com.tcellpair3.cartservice.core.dtos.AddressResponse;
import com.tcellpair3.cartservice.core.dtos.ProductResponse;
import com.tcellpair3.cartservice.core.dtos.ProductResponse;
import com.tcellpair3.cartservice.entities.Cart;
import com.tcellpair3.cartservice.repository.CartRepository;
import com.tcellpair3.cartservice.service.abstracts.CartService;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.turkcell.tcell.exception.exceptions.type.BaseBusinessException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CustomerInvoiceClient customerInvoiceClient;
    private final ProductServiceClient productServiceClient;
    private final AddressClient addressClient;
    private final OrderClient orderClient;
    @Override
    public void addProductToCustomer(Integer customerInvoiceId, Integer productId,Integer addressId) {
        // müşteri fatura hesabı sorgulama
        // ResponseEntity<Boolean> customerInvoiceExists = customerInvoiceClient.doesCustomerInvoiceExist(customerInvoceId);
        try {
            ResponseEntity<Boolean> customerInvoiceExistsResponse = customerInvoiceClient.doesCustomerInvoiceExist(customerInvoiceId);
            if (customerInvoiceExistsResponse.getStatusCode() == HttpStatus.NOT_FOUND || !Boolean.TRUE.equals(customerInvoiceExistsResponse.getBody())) {
                throw new BaseBusinessException("Customer invoice not found");
            }
        } catch (HttpClientErrorException.NotFound ex) {
            throw new BaseBusinessException("Customer invoice not found");
        } catch (Exception ex) {
            throw new ServiceException("An error occurred while checking if customer invoice exists", ex);
        }

        // address sorgulama
        boolean addressExists;
        try {
            addressExists = addressClient.doesAddressExist(addressId);
            if (!addressExists) {
                throw new BaseBusinessException("Address not found");
            }
        } catch (HttpClientErrorException.NotFound ex) {
            throw new BaseBusinessException("Address not found");
        } catch (Exception ex) {
            throw new ServiceException("An error occurred while checking if address exists", ex);
        }

        ProductResponse product = productServiceClient.getProductDetails(productId);
        AddressResponse address = addressClient.addressDetails(addressId);

        Cart cart = new Cart();
        cart.setCustomerInvoiceId(customerInvoiceId);
        cart.setProductNo(product.getProductNo());
        cart.setProductName(product.getName());
        cart.setPrice(product.getPrice());
        cart.setAddressDescription(address.getAddressDescription());
        cart.setCity(address.getCity());
        cart.setStreet(address.getStreet());
        cart.setHouseFlatNumber(address.getHouseFlatNumber());
        cart.setDistrict(address.getDistrict());
        orderClient.createOrder(cart);
        cartRepository.save(cart);
    }

    @Override
    public void deleteCart(String cartId) {
        cartRepository.deleteById(cartId);
    }

    @Override
    public Optional<Cart> getByIdCart(String cartId) {
        return cartRepository.findById(cartId);
    }

    @Override
    public List<Cart> getCartsByCustomerInvoiceId(Integer customerInvoiceId) {
        return cartRepository.findByCustomerInvoiceId(customerInvoiceId);
    }

    @Override
    public double getTotalPriceByCustomerInvoiceId(Integer customerInvoiceId) {
        List<Cart> carts = cartRepository.findByCustomerInvoiceId(customerInvoiceId);
        return carts.stream()
                .mapToDouble(Cart::getPrice)
                .sum();
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }
}