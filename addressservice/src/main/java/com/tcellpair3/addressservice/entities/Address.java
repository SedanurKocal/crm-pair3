package com.tcellpair3.addressservice.entities;

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
public class Address{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;
    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "city")
    private City city;

    @Column(name = "district")
    @NotBlank(message = "This field is required")
    private String district;

    @Column(name = "street")
    @NotBlank(message = "This field is required")
    private String street;

    @Column(name = "house_flat_number")
    @NotBlank(message = "This field is required")
    private String houseFlatNumber;

    @Column(name = "address_description")
    @NotBlank(message = "This field is required")
    private String addressDescription;

    @Column(name = "is_default")
    private boolean isDefault;


}
