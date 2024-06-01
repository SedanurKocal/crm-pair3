package com.tcellpair3.productservice.core.dtos.response.catalog;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCatalogsResponse {
    private int id;

    private String name;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private boolean isDeleted;
}
