package com.tcellpair3.addressservice.core.dto.responses;

import com.tcellpair3.addressservice.entities.City;
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

    private int customerInvoiceId;

    private City city;

    private String district;

    private String street;

    private String houseFlatNumber;

    private String addressDescription;

    private boolean isDefault;
}
