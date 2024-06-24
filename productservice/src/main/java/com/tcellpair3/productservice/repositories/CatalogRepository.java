package com.tcellpair3.productservice.repositories;

import com.tcellpair3.productservice.entities.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CatalogRepository extends JpaRepository<Catalog,Integer> {

    @Query("SELECT c FROM Catalog c WHERE c.id = :id AND c.isDeleted = false")
    Optional<Catalog> findByIdAndNotDeleted(int id);

    @Query("SELECT c FROM Catalog c WHERE c.isDeleted = false")
    List<Catalog> findAllNotDeleted();
}
