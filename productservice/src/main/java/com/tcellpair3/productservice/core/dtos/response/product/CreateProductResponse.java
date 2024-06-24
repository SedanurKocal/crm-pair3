package com.tcellpair3.productservice.core.dtos.response.product;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateProductResponse {

    private int id;

    private int productNo;

    private String name;

    private LocalDateTime createdDate;

    private boolean isDeleted;

    private int catalogId;
}
