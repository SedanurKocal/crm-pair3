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

    private boolean isDeleted;
    private LocalDateTime createdDate;
    private double price;

    private int catalogId;
}
