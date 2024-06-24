package com.tcellpair3.productservice.core.dtos.response.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchResultsResponse {

    private int id;

    private int productNo;

    private String name;
}
