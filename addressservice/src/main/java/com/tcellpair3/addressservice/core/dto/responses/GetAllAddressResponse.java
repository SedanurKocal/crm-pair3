package com.tcellpair3.addressservice.core.dto.responses;

import com.tcellpair3.addressservice.entities.City;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllAddressResponse {
    private int addressId;

    private int customerId;

    private City city;

    @NotBlank(message = "This field is required")
    private String district;

    @NotBlank(message = "This field is required")
    private String street;

    @NotBlank(message = "This field is required")
    private String houseFlatNumber;

    @NotBlank(message = "This field is required")
    private String addressDescription;

    private boolean isDefault;
}
