package com.tcellpair3.productservice.service.concretes;


import com.tcellpair3.productservice.core.dtos.request.product.CreateProductRequest;
import com.tcellpair3.productservice.core.dtos.response.product.*;
import com.tcellpair3.productservice.repositories.ProductRepository;
import com.tcellpair3.productservice.service.abstracts.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public CreateProductResponse createProduct(CreateProductRequest createProductRequest) {
        return null;
    }

    @Override
    public List<GetAllProductsResponse> getAllProducts() {
        return null;
    }

    @Override
    public Optional<GetProductByIdResponse> getProductById(int id) {
        return Optional.empty();
    }

    @Override
    public UpdateProductResponse updateProducts(int id) {
        return null;
    }

    @Override
    public void deleteProduct(int id) {

    }

    @Override
    public List<SearchResultsResponse> findByName(String name) {
        return null;
    }

    @Override
    public List<SearchResultsResponse> findByProductNo(int productNo) {
        return null;
    }
}
