package com.tcellpair3.customerservice.service.abstracts;

import com.tcellpair3.customerservice.core.dtos.requests.contactmedium.CreateContactMediumRequest;
import com.tcellpair3.customerservice.core.dtos.requests.contactmedium.UpdateContactMediumRequest;
import com.tcellpair3.customerservice.core.dtos.responses.contactmedium.*;

import java.util.List;
import java.util.Optional;

public interface ContactMediumService {

    CreateContactMediumResponse createContactMedium(CreateContactMediumRequest request);
    UpdateContactMediumResponse updateContactMedium(int id ,UpdateContactMediumRequest request);
    void deleteContactMedium(int id);
    List<GetAllContactMediumResponse> getAllContactMedium();
    Optional<GetContactMediumByIdResponse> getByContactMediumId(int id);

    List<ContactMediumWithCustomerResponse> findByCustomerId(int customerId);

}
