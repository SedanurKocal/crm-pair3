package com.tcellpair3.productservice.repositories;

import com.tcellpair3.productservice.entities.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<Catalog,Integer> {
}
