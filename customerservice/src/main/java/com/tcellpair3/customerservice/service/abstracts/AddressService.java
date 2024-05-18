package com.tcellpair3.customerservice.service.abstracts;

import com.tcellpair3.customerservice.core.dtos.requests.address.CreateAddressRequest;
import com.tcellpair3.customerservice.core.dtos.requests.address.UpdateAddressRequest;
import com.tcellpair3.customerservice.core.dtos.responses.address.CreateAddressResponse;
import com.tcellpair3.customerservice.core.dtos.responses.address.GetAllAddressResponse;
import com.tcellpair3.customerservice.core.dtos.responses.address.GetByIdAddressResponse;
import com.tcellpair3.customerservice.core.dtos.responses.address.UpdateAddressResponse;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    CreateAddressResponse createAddress(CreateAddressRequest request);

    UpdateAddressResponse updateAddress(int id,UpdateAddressRequest request);

    void deleteAddress(int id);

    List<GetAllAddressResponse> getAllAddresses();

    Optional<GetByIdAddressResponse> getByIdAddress(int id);

}
