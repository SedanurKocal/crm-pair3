package com.tcellpair3.cartservice.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {

    private int addressId;

    private int customerId;

    private String city;

    private String district;

    private String street;

    private String houseFlatNumber;

    private String addressDescription;
}