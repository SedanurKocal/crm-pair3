package com.tcellpair3.customerservice.core.mappers;

import com.tcellpair3.customerservice.core.dtos.requests.address.CreateAddressRequest;
import com.tcellpair3.customerservice.core.dtos.requests.address.UpdateAddressRequest;
import com.tcellpair3.customerservice.core.dtos.responses.address.GetAllAddressResponse;
import com.tcellpair3.customerservice.core.dtos.responses.address.GetByIdAddressResponse;
import com.tcellpair3.customerservice.entities.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    @Mapping(source = "customerId", target = "customer.id")

    Address createAddressMapper(CreateAddressRequest request);


    @Mapping(source = "customer.id", target = "customerId")
    GetByIdAddressResponse getByIdAddressMapper(Address address);

    @Mapping(source = "customer.id", target = "customerId")
    GetAllAddressResponse getAllAddressMapper(Address address);


    List<GetAllAddressResponse> AddressToListAddressResponses(List<Address> addressList);

    Address updateAddress(UpdateAddressRequest updateAddressRequest, @MappingTarget Address address);



}

