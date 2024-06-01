package com.tcellpair3.productservice.core.mappers;

import com.tcellpair3.productservice.core.dtos.request.product.CreateProductRequest;
import com.tcellpair3.productservice.core.dtos.request.product.UpdateProductRequest;
import com.tcellpair3.productservice.core.dtos.response.product.GetAllProductsResponse;
import com.tcellpair3.productservice.core.dtos.response.product.GetProductByIdResponse;
import com.tcellpair3.productservice.core.dtos.response.product.SearchResultsResponse;
import com.tcellpair3.productservice.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    //@Mapping(target = "productNo", source = "productNo")
    @Mapping(target = "catalog.id", source = "catalogId")
    @Mapping(target = "updatedDate", ignore = true)
    Product createProductMapper(CreateProductRequest product);


    @Mapping(target = "catalogId", source = "catalog.id")
    GetProductByIdResponse getProductByIdMapper(Product product);

    @Mapping(target = "catalogId", source = "catalog.id")
    GetAllProductsResponse getAllProductsMapper(Product product);

    SearchResultsResponse productToSearchResultsResponse(Product product);

    List<SearchResultsResponse> productsToSearchResultsResponses(List<Product> products);


    List<GetAllProductsResponse> getProductsListMapper(List<Product> product);
    //@Mapping(target = "productNo", source = "productNo")
    @Mapping(target = "createdDate", ignore = true)
    Product updateProductMapper(UpdateProductRequest request, @MappingTarget Product product);
}
