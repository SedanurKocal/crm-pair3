package com.tcellpair3.orderservice.core.dtos.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {
    private String id;

    private int productNo;

    private double price;

    private String productName;

    private int customerInvoiceId;

    private String city;

    private String district;

    private String street;

    private String houseFlatNumber;

    private String addressDescription;
}
