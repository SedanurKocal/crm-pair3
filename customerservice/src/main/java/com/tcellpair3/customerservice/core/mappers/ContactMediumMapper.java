package com.tcellpair3.customerservice.core.mappers;

import com.tcellpair3.customerservice.core.dtos.requests.contactmedium.CreateContactMediumRequest;
import com.tcellpair3.customerservice.core.dtos.requests.contactmedium.UpdateContactMediumRequest;
import com.tcellpair3.customerservice.core.dtos.responses.contactmedium.ContactMediumWithCustomerResponse;
import com.tcellpair3.customerservice.core.dtos.responses.contactmedium.GetAllContactMediumResponse;
import com.tcellpair3.customerservice.core.dtos.responses.contactmedium.GetByIdContactMediumResponse;
import com.tcellpair3.customerservice.entities.ContactMedium;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ContactMediumMapper {

    ContactMediumMapper INSTANCE = Mappers.getMapper(ContactMediumMapper.class);

    @Mapping(source = "customerId", target = "customer.id")
    ContactMedium createContactMediumMapper(CreateContactMediumRequest request);


    @Mapping(source = "customer.id", target = "customerId")
    GetByIdContactMediumResponse getByIdContactMediumMapper(ContactMedium contactMedium);


    @Mapping(source = "customer.id", target = "customerId")
    GetAllContactMediumResponse contactMediumToResponse(ContactMedium contactMedium);

    List<GetAllContactMediumResponse> contactMediumToListContactResponses(List<ContactMedium> contactMedium);

    ContactMedium updateContactMedium(UpdateContactMediumRequest contactMediumRequest, @MappingTarget ContactMedium contactMedium);

    @Mapping(source = "customer.id", target = "customerId")
    ContactMediumWithCustomerResponse toDto(ContactMedium contactMedium);

    List<ContactMediumWithCustomerResponse> toDtoList(List<ContactMedium> contactMediums);
}
