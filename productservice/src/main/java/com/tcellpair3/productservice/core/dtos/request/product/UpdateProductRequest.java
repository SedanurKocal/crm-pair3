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
public class UpdateProductRequest {

    private String name;

    private boolean isDeleted;
    private LocalDateTime updatedDate;

    private double price;

    private int catalogId;
}
