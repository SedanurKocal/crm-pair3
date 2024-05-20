package com.tcellpair3.customerservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "addresses")
public class Address extends BaseEntity{

    @Column(name = "city")
    private City city; //TO DO : TR de citylerin kontrolü için api?

    @Column(name = "district")
    private String district;

    @Column(name = "street")
    private String street;

    @Column(name = "house_flat_number")
    private String houseFlatNumber;

    @Column(name = "address_description")
    private String addressDescription;

    @Column(name = "is_default")
    private boolean isDefault;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
