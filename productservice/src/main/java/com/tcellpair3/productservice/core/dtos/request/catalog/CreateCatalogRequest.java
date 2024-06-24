package com.tcellpair3.productservice.core.dtos.request.catalog;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
public class CreateCatalogRequest {

    private String name;
    private LocalDateTime createdDate;
    private boolean isDeleted;


}
