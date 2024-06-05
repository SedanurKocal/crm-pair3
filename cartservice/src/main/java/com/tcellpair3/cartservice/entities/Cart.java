package com.tcellpair3.cartservice.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "cartservice")
public class Cart {
    @Id
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