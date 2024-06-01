package com.tcellpair3.productservice.core.mappers;

import com.tcellpair3.productservice.core.dtos.request.product.CreateProductRequest;
import com.tcellpair3.productservice.core.dtos.request.product.UpdateProductRequest;
import com.tcellpair3.productservice.core.dtos.response.product.GetAllProductsResponse;
import com.tcellpair3.productservice.core.dtos.response.product.GetProductByIdResponse;
import com.tcellpair3.productservice.core.dtos.response.product.SearchResultsResponse;
import com.tcellpair3.productservice.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    //@Mapping(target = "productNo", source = "productNo")
    Product createProductMapper(CreateProductRequest product);
    GetProductByIdResponse getProductByIdMapper(Product product);
    GetAllProductsResponse getAllProductsMapper(Product product);
    SearchResultsResponse searchResultMapper(Product product);
    List<GetAllProductsResponse> getProductsListMapper(Product product);
    //@Mapping(target = "productNo", source = "productNo")
    Product updateProductMapper(UpdateProductRequest request, Product product);
}
