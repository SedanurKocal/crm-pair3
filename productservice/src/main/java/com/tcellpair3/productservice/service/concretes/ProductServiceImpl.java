package com.tcellpair3.productservice.service.concretes;


import com.tcellpair3.productservice.core.dtos.request.product.CreateProductRequest;
import com.tcellpair3.productservice.core.dtos.request.product.UpdateProductRequest;
import com.tcellpair3.productservice.core.dtos.response.product.*;
import com.tcellpair3.productservice.core.exception.type.BusinessException;
import com.tcellpair3.productservice.core.mappers.ProductMapper;
import com.tcellpair3.productservice.entities.Product;
import com.tcellpair3.productservice.repositories.ProductRepository;
import com.tcellpair3.productservice.service.abstracts.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public CreateProductResponse createProduct(CreateProductRequest createProductRequest) {
        createProductRequest.setDeleted(false);
        Product product = ProductMapper.INSTANCE.createProductMapper(createProductRequest);
        Product savedProduct = productRepository.save(product);
        return new CreateProductResponse(savedProduct.getId(),savedProduct.getProductNo(),savedProduct.getName(),savedProduct.getCreatedDate(),savedProduct.isDeleted(),savedProduct.getCatalog().getId());
    }

    @Override
    public List<GetAllProductsResponse> getAllProducts() {
        List<Product> products = productRepository.findAllNotDeleted();
        return ProductMapper.INSTANCE.getProductsListMapper(products);
    }

    @Override
    public GetProductByIdResponse getProductById(int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return new GetProductByIdResponse(product.getName(),product.getProductNo(),product.getPrice(), product.getCreatedDate(), product.getUpdatedDate(),product.getCatalog().isDeleted(), product.getCatalog().getId());
    }

    @Override
    public UpdateProductResponse updateProducts(int id, UpdateProductRequest request) {
        request.setDeleted(false);
        Optional<Product> productOptional = productRepository.findById(id);
        Product existingProduct = productOptional.get();
        Product product = ProductMapper.INSTANCE.updateProductMapper(request,existingProduct);
        Product updatedProduct = productRepository.save(product);
        return new UpdateProductResponse(updatedProduct.getId(),updatedProduct.getProductNo(),updatedProduct.getName(),updatedProduct.getUpdatedDate(),updatedProduct.isDeleted(),updatedProduct.getCatalog().getId());
    }

    @Override
    public void deleteProduct(int id) {
        Product product = productRepository.findById(id).get();
        product.setDeleted(true);
        productRepository.save(product);
    }

    public List<SearchResultsResponse> findByName(String name) {
        List<Product> products = productRepository.findByNameContainingAndNotDeleted(name);
        return ProductMapper.INSTANCE.productsToSearchResultsResponses(products);
    }

    public List<SearchResultsResponse> findByProductNo(int productNo) {
        List<Product> products = productRepository.findByProductNoContainingAndNotDeleted(productNo);
        return ProductMapper.INSTANCE.productsToSearchResultsResponses(products);
    }

    @Override
    public List<SearchResultsResponse> findByCatalogName(String catalogName) {
        List<Product> products = productRepository.findByCatalogNameAndNotDeleted(catalogName);
        return ProductMapper.INSTANCE.productsToSearchResultsResponses(products);
    }
}
