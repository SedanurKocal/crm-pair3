package com.tcellpair3.customerservice.core.dtos.responses.address;

import com.tcellpair3.customerservice.entities.City;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAddressResponse {

    int id;

    private City city; //TO DO : TR de citylerin kontrolü için api?

    private String district;


    private String street;

    private String houseFlatNumber;

    private boolean isDefault;
    private String addressDescription;

    private int customerId;
}
