package com.tcellpair3.productservice.controller;


import com.tcellpair3.productservice.service.abstracts.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
//@SecurityRequirement(name = "bearerAuth")
public class ProductController {
   private final ProductService productService;

}
