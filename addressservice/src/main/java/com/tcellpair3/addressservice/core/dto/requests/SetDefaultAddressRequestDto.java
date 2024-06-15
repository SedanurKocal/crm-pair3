package com.tcellpair3.addressservice.core.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SetDefaultAddressRequestDto {

    private int customerId;

    private int addressId;
}
