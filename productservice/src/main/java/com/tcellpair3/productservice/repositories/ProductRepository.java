package com.tcellpair3.productservice.repositories;

import com.tcellpair3.productservice.core.dtos.response.product.SearchResultsResponse;
import com.tcellpair3.productservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query("SELECT p FROM Product p WHERE p.id = :id AND p.isDeleted = false")
    Optional<Product> findByIdAndNotDeleted(int id);

    @Query("SELECT p FROM Product p WHERE p.isDeleted = false")
    List<Product> findAllNotDeleted();

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:productName% AND p.isDeleted = false")
    List<Product> findByNameContainingAndNotDeleted(String productName);

    @Query("SELECT p FROM Product p WHERE p.productNo = :productNo AND p.isDeleted = false")
    List<Product> findByProductNoContainingAndNotDeleted(int productNo);

    @Query("SELECT p FROM Product p WHERE p.catalog.name = :catalogName AND p.isDeleted = false")
    List<Product> findByCatalogNameAndNotDeleted(String catalogName);
}


