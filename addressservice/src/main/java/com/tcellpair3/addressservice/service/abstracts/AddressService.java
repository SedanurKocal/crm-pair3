package com.tcellpair3.addressservice.service.abstracts;

import com.tcellpair3.addressservice.core.dto.requests.CreateAddressRequest;
import com.tcellpair3.addressservice.core.dto.requests.UpdateAddressRequest;
import com.tcellpair3.addressservice.core.dto.responses.*;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    List<GetAddressByCustomerIdResponse> getAddressesByCustomerId(Integer customerId);
    List<GetAllAddressResponse> getAllAddress();
    Optional<GetByAddressIdResponse> getByIdAddress(int id);
    CreateAddressResponse createAddress(CreateAddressRequest request);
    UpdateAddressResponse updateAddress(int id, UpdateAddressRequest address);
    void deleteAddress(int id);
    boolean existById(int id);
}
