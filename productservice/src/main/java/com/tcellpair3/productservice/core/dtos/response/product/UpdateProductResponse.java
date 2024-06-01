package com.tcellpair3.productservice.core.dtos.response.product;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateProductResponse {
    private int id;
    private int productNo;
    private String name;
    private LocalDateTime updatedDate;
    private boolean isDeleted;
    private int catalogId;

    public UpdateProductResponse(int id, int productNo, String name, LocalDateTime updatedDate, boolean isDeleted, int catalogId) {
        this.id = id;
        this.productNo = productNo;
        this.name = name;
        this.updatedDate = updatedDate;
        this.isDeleted = isDeleted;
        this.catalogId = catalogId;
    }
}
