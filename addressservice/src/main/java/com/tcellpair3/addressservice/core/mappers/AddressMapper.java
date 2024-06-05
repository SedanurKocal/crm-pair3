package com.tcellpair3.addressservice.core.mappers;

import com.tcellpair3.addressservice.core.dto.requests.CreateAddressRequest;
import com.tcellpair3.addressservice.core.dto.requests.UpdateAddressRequest;
import com.tcellpair3.addressservice.core.dto.responses.GetAddressByCustomerIdResponse;
import com.tcellpair3.addressservice.core.dto.responses.GetAllAddressResponse;
import com.tcellpair3.addressservice.core.dto.responses.GetByAddressIdResponse;
import com.tcellpair3.addressservice.entities.Address;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    //CreateAddressResponse addressToAddressDTO(Address address);
    Address createAddressMapper(CreateAddressRequest request);

    Address updateAddressMapper(UpdateAddressRequest addressRequest, @MappingTarget Address address);

    GetByAddressIdResponse getByAddressIdMapper(Address address);

    List<GetAddressByCustomerIdResponse> getAllAddressByCustomerId(List<Address> addresses);
    GetAddressByCustomerIdResponse getAddressByCustomerId(Address address);
    GetAllAddressResponse getAllAddressMapper(Address address);
    List<GetAllAddressResponse> addressToListAddressResponse(List<Address> addresses);



}
