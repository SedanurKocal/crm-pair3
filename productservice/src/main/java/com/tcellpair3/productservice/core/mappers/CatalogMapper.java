package com.tcellpair3.productservice.core.mappers;

import com.tcellpair3.productservice.core.dtos.request.catalog.CreateCatalogRequest;
import com.tcellpair3.productservice.core.dtos.request.catalog.UpdateCatalogRequest;
import com.tcellpair3.productservice.core.dtos.response.catalog.GetAllCatalogsResponse;
import com.tcellpair3.productservice.core.dtos.response.catalog.GetCatalogByIdResponse;
import com.tcellpair3.productservice.entities.Catalog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CatalogMapper {

    CatalogMapper INSTANCE = Mappers.getMapper(CatalogMapper.class);

    @Mapping(target = "updatedDate", ignore = true)
    Catalog createCatalogMapper(CreateCatalogRequest catalog);

    GetCatalogByIdResponse getCatalogByIdMapper(Catalog catalog);

    GetAllCatalogsResponse getAllCatalogMapper(Catalog catalog);

    List<GetAllCatalogsResponse> catalogToListCatalogResponses(List<Catalog> catalogList);

    @Mapping(target = "createdDate", ignore = true)
    Catalog updateCatalogMapper(UpdateCatalogRequest catalogRequest, @MappingTarget Catalog catalog);
}
