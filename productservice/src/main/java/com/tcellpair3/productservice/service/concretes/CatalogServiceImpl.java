package com.tcellpair3.productservice.service.concretes;

import com.tcellpair3.productservice.core.dtos.request.catalog.CreateCatalogRequest;
import com.tcellpair3.productservice.core.dtos.request.catalog.UpdateCatalogRequest;
import com.tcellpair3.productservice.core.dtos.response.catalog.CreateCatalogResponse;
import com.tcellpair3.productservice.core.dtos.response.catalog.GetAllCatalogsResponse;
import com.tcellpair3.productservice.core.dtos.response.catalog.GetCatalogByIdResponse;
import com.tcellpair3.productservice.core.dtos.response.catalog.UpdateCatalogResponse;
import com.tcellpair3.productservice.service.abstracts.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {
    @Override
    public CreateCatalogResponse createCatalog(CreateCatalogRequest request) {
        return null;
    }

    @Override
    public UpdateCatalogResponse updateCatalog(UpdateCatalogRequest request) {
        return null;
    }

    @Override
    public void deleteCatalog(int id) {

    }

    @Override
    public List<GetAllCatalogsResponse> getAllCatalog() {
        return null;
    }

    @Override
    public Optional<GetCatalogByIdResponse> getByCatalogId(int id) {
        return Optional.empty();
    }
}
