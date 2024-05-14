package com.tcellpair3.customerservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "city")
    @NotBlank(message = "This field is required")
    private City city; //TO DO : TR de citylerin kontrolü için api?

    @Column(name = "district")
    @NotBlank(message = "This field is required")
    private String district;

    @Column(name = "street")
    @NotBlank(message = "This field is required")
    private String street;

    @Column(name = "house_flat_number")
    @NotBlank(message = "This field is required")
    private String houseFlatNumber; //TO DO: api?

    @Column(name = "address_description")
    @NotBlank(message = "This field is required")
    private String addressDescription;

}
