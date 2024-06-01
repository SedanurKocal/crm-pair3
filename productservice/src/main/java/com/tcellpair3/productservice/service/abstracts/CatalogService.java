package com.tcellpair3.productservice.service.abstracts;

import com.tcellpair3.productservice.core.dtos.request.catalog.CreateCatalogRequest;
import com.tcellpair3.productservice.core.dtos.request.catalog.UpdateCatalogRequest;
import com.tcellpair3.productservice.core.dtos.response.catalog.CreateCatalogResponse;
import com.tcellpair3.productservice.core.dtos.response.catalog.GetAllCatalogsResponse;
import com.tcellpair3.productservice.core.dtos.response.catalog.GetCatalogByIdResponse;
import com.tcellpair3.productservice.core.dtos.response.catalog.UpdateCatalogResponse;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Optional;

public interface CatalogService {

    CreateCatalogResponse createCatalog(CreateCatalogRequest request);
    UpdateCatalogResponse updateCatalog(int id,UpdateCatalogRequest request);

    void deleteCatalog(int id);

    List<GetAllCatalogsResponse> getAllCatalog();

    Optional<GetCatalogByIdResponse> getByCatalogId(int id);

}
