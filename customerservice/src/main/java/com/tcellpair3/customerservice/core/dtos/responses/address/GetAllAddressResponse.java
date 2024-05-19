package com.tcellpair3.customerservice.core.dtos.responses.address;

import com.tcellpair3.customerservice.enums.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllAddressResponse {

    int id;

    private City city; //TO DO : TR de citylerin kontrolü için api?

    private String district;
    private boolean isDefault;

    private String street;

    private String houseFlatNumber;


    private String addressDescription;

    private int customerId;
}
