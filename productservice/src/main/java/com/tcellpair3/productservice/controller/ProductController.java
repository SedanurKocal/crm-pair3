package com.tcellpair3.productservice.controller;


import com.tcellpair3.productservice.core.dtos.request.catalog.CreateCatalogRequest;
import com.tcellpair3.productservice.core.dtos.request.product.CreateProductRequest;
import com.tcellpair3.productservice.core.dtos.request.product.UpdateProductRequest;
import com.tcellpair3.productservice.core.dtos.response.catalog.CreateCatalogResponse;
import com.tcellpair3.productservice.core.dtos.response.product.*;
import com.tcellpair3.productservice.service.abstracts.ProductService;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
//@SecurityRequirement(name = "bearerAuth")
public class ProductController {
   private final ProductService productService;
   @GetMapping("/getAll")
   public List<GetAllProductsResponse> getAllProducts()
   {
      return productService.getAllProducts();
   }
   @GetMapping("/{id}")
   public Optional<GetProductByIdResponse> getProductById(@PathVariable int id)
   {
      return productService.getProductById(id);
   }
   @GetMapping("findByName")
   public List<SearchResultsResponse> findByProductName(@RequestParam String productName)
   {
      return productService.findByName(productName);
   }
   @GetMapping("/productNo/{productNo}")
   public List<SearchResultsResponse> getProductsByProductNo(@PathVariable int productNo) {
      return productService.findByProductNo(productNo);
   }
   @GetMapping("/catalog/name/{catalogName}")
   public List<SearchResultsResponse> getProductsByCatalogName(@PathVariable String catalogName) {
      return productService.findByCatalogName(catalogName);
   }
   @PostMapping("/create")
   public CreateProductResponse createProduct(@RequestBody CreateProductRequest request)
   {
      return productService.createProduct(request);
   }
   @PutMapping("/update/{id}")
   public UpdateProductResponse updateProduct(@PathVariable int id, @RequestBody UpdateProductRequest request)
   {
      return productService.updateProducts(id,request);
   }
   @DeleteMapping("/delete/{id}")
   public void deletedProduct(@PathVariable int id)
   {
      productService.deleteProduct(id);
   }
}
