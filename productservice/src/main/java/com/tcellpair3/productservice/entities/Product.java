package com.tcellpair3.productservice.entities;

import com.tcellpair3.productservice.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(name="product_no",unique = true)
    private int productNo;

    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "catalog_id")
    private Catalog catalog;

    @PrePersist
    private void setProductNo() {
        if (this.productNo == 0) {
            this.productNo = (int) (Math.random() * 100);
        }
    }
}
