package com.tcellpair3.productservice.controller;

import com.tcellpair3.productservice.core.dtos.request.catalog.CreateCatalogRequest;
import com.tcellpair3.productservice.core.dtos.request.catalog.UpdateCatalogRequest;
import com.tcellpair3.productservice.core.dtos.response.catalog.CreateCatalogResponse;
import com.tcellpair3.productservice.core.dtos.response.catalog.GetAllCatalogsResponse;
import com.tcellpair3.productservice.core.dtos.response.catalog.GetCatalogByIdResponse;
import com.tcellpair3.productservice.core.dtos.response.catalog.UpdateCatalogResponse;
import com.tcellpair3.productservice.core.exception.type.BusinessException;
import com.tcellpair3.productservice.service.abstracts.CatalogService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/catalog")
@RequiredArgsConstructor
public class CatalogController {
    private final CatalogService catalogService;


    @GetMapping("/getAll")
    public List<GetAllCatalogsResponse> getAllCatalogs()
    {
        return catalogService.getAllCatalog();
    }
    @GetMapping("/{id}")
    public Optional<GetCatalogByIdResponse> getCatalogById(@PathVariable int id) {
        return catalogService.getByCatalogId(id);
    }
    @PostMapping("/create")
    public CreateCatalogResponse createCatalog(@RequestBody CreateCatalogRequest request)
    {
        return catalogService.createCatalog(request);
    }
    @PutMapping("/update/{id}")
    public UpdateCatalogResponse updateCatalog(@PathVariable int id,@RequestBody UpdateCatalogRequest request)
    {
        return catalogService.updateCatalog(id,request);
    }
    @DeleteMapping("delete/{id}")
    public void deleteCatalog(@PathVariable int id)
    {
         catalogService.deleteCatalog(id);
    }
}
