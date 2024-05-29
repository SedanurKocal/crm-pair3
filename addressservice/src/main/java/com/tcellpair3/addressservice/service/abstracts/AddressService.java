package com.tcellpair3.addressservice.service.abstracts;

import com.tcellpair3.addressservice.core.dto.responses.AddressDtoResponse;
import com.tcellpair3.addressservice.entities.Address;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    List<Address> getAddressesByCustomerId(Integer customerId);
    List<Address> getAllAddress();
    Optional<Address> getByIdAddress(int id);
    AddressDtoResponse createAddress(AddressDtoResponse request);
    Address updateAddress(int id,Address address);
    void deleteAddress(int id);

}
