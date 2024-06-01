package com.tcellpair3.productservice.core.dtos.request.product;

import com.tcellpair3.productservice.entities.Catalog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {
    private String name;

    private int productNo;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private boolean isDeleted;

    private double price;

    private Catalog catalog;
}
