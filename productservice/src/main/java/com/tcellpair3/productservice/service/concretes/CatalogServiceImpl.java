package com.tcellpair3.productservice.service.concretes;

import com.tcellpair3.productservice.core.dtos.request.catalog.CreateCatalogRequest;
import com.tcellpair3.productservice.core.dtos.request.catalog.UpdateCatalogRequest;
import com.tcellpair3.productservice.core.dtos.response.catalog.CreateCatalogResponse;
import com.tcellpair3.productservice.core.dtos.response.catalog.GetAllCatalogsResponse;
import com.tcellpair3.productservice.core.dtos.response.catalog.GetCatalogByIdResponse;
import com.tcellpair3.productservice.core.dtos.response.catalog.UpdateCatalogResponse;
import com.tcellpair3.productservice.core.mappers.CatalogMapper;
import com.tcellpair3.productservice.entities.Catalog;
import com.tcellpair3.productservice.repositories.CatalogRepository;
import com.tcellpair3.productservice.service.abstracts.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.turkcell.tcell.exception.exceptions.type.BaseBusinessException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository catalogRepository;

    @Override
    public CreateCatalogResponse createCatalog(CreateCatalogRequest request) {
         request.setDeleted(false);
         Catalog catalog = CatalogMapper.INSTANCE.createCatalogMapper(request);
         Catalog savedCatalog = catalogRepository.save(catalog);
         return new CreateCatalogResponse(savedCatalog.getId(),savedCatalog.getName(),savedCatalog.getCreatedDate(),savedCatalog.isDeleted());
    }

    @Override
    public UpdateCatalogResponse updateCatalog(int id,UpdateCatalogRequest request) {
        request.setDeleted(false);
       Optional<Catalog> catalogOptional = catalogRepository.findById(id);
       Catalog existingCatalog = catalogOptional.get();
       Catalog catalog = CatalogMapper.INSTANCE.updateCatalogMapper(request,existingCatalog);
       Catalog savedCatalog = catalogRepository.save(catalog);
        return new UpdateCatalogResponse(savedCatalog.getId(),savedCatalog.getName(),savedCatalog.getUpdatedDate(),savedCatalog.isDeleted());
    }

    @Override
    public void deleteCatalog(int id) {
        Catalog catalog = catalogRepository.findById(id).get();
        catalog.setDeleted(true);
        catalogRepository.save(catalog);
    }

    @Override
    public List<GetAllCatalogsResponse> getAllCatalog() {
        List<Catalog> catalogs = catalogRepository.findAllNotDeleted();
        return CatalogMapper.INSTANCE.catalogToListCatalogResponses(catalogs);
    }

    @Override
    public Optional<GetCatalogByIdResponse> getByCatalogId(int id) {
        Optional<Catalog> catalogOptional = catalogRepository.findByIdAndNotDeleted(id);
        if(catalogOptional.isEmpty())
        {
            throw new BaseBusinessException("Catalog not found");
        }
        return catalogOptional.map(CatalogMapper.INSTANCE::getCatalogByIdMapper);
    }
}
