package com.tcellpair3.productservice.repositories;

import com.tcellpair3.productservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
