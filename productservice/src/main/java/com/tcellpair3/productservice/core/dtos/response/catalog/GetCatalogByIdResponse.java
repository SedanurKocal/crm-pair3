package com.tcellpair3.productservice.core.dtos.response.catalog;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetCatalogByIdResponse {

    private int id;
    private String name;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private boolean isDeleted;

}
