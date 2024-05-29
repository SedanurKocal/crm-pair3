package com.tcellpair3.addressservice.core.mappers;

import com.tcellpair3.addressservice.core.dto.responses.AddressDtoResponse;
import com.tcellpair3.addressservice.entities.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    AddressDtoResponse addressToAddressDTO(Address address);
    Address addressDTOToAddress(AddressDtoResponse addressDTO);
}
