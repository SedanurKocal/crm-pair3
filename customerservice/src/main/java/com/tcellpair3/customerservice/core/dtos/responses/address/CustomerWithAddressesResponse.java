package com.tcellpair3.customerservice.core.dtos.responses.address;

import com.tcellpair3.addressservice.entities.Address;
import com.tcellpair3.customerservice.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerWithAddressesResponse {
    private Customer customer;
    private List<Address> addresses;
}
