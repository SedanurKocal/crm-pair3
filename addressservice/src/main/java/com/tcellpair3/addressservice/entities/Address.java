package com.tcellpair3.addressservice.entities;

import com.tcellpair3.addressservice.core.entities.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
public class Address extends BaseEntity {

    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "city")
    private City city;

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
}
