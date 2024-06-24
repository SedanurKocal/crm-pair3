package com.tcellpair3.cartservice.entities;

import com.tcellpair3.cartservice.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "cartservice")
public class Cart extends BaseEntity {

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