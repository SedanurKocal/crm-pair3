package com.tcellpair3.customerservice.core.dtos.requests.address;

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
public class UpdateAddressRequest {


    private City city; //TO DO : TR de citylerin kontrolü için api?

    @NotBlank(message = "This field is required")
    private String district;

    @NotBlank(message = "This field is required")
    private String street;

    @NotBlank(message = "This field is required")
    private String houseFlatNumber;

    private boolean isDefault;
    @NotBlank(message = "This field is required")
    private String addressDescription;


}
