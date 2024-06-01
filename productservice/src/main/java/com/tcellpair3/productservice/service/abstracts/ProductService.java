package com.tcellpair3.productservice.service.abstracts;

import com.tcellpair3.productservice.core.dtos.request.product.CreateProductRequest;
import com.tcellpair3.productservice.core.dtos.request.product.UpdateProductRequest;
import com.tcellpair3.productservice.core.dtos.response.product.*;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    CreateProductResponse createProduct(CreateProductRequest createProductRequest);
    List<GetAllProductsResponse> getAllProducts();
    Optional<GetProductByIdResponse> getProductById(int id);
    UpdateProductResponse updateProducts(int id, UpdateProductRequest request);
    void deleteProduct (int id);
    List<SearchResultsResponse> findByName(String name);

    List<SearchResultsResponse> findByProductNo(int productNo);
    List<SearchResultsResponse> findByCatalogName(String catalogName);
}
