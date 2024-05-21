package com.tcellpair3.customerservice.service.concretes;

import com.tcellpair3.customerservice.core.dtos.requests.contactmedium.CreateContactMediumRequest;
import com.tcellpair3.customerservice.core.dtos.requests.contactmedium.UpdateContactMediumRequest;
import com.tcellpair3.customerservice.core.dtos.responses.contactmedium.*;
import com.tcellpair3.customerservice.core.exception.type.BusinessException;
import com.tcellpair3.customerservice.core.mappers.ContactMediumMapper;
import com.tcellpair3.customerservice.core.service.Abstract.ContactMediumValidationService;
import com.tcellpair3.customerservice.entities.ContactMedium;
import com.tcellpair3.customerservice.repositories.ContactMediumRepository;
import com.tcellpair3.customerservice.repositories.CustomerRepository;
import com.tcellpair3.customerservice.service.abstracts.ContactMediumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactMediumServiceImpl implements ContactMediumService {
    private final ContactMediumRepository contactMediumRepository;
    private final ContactMediumValidationService contactMediumServiceValidation;
    private final CustomerRepository customerRepository;
    @Override
    public CreateContactMediumResponse createContactMedium(CreateContactMediumRequest request) {
       if(!customerRepository.existsById(request.getCustomerId())){
            throw new BusinessException("kullanıcı bulunamadı");
        }
        contactMediumServiceValidation.validatePhoneNumber(request.getMobilePhone());
        ContactMedium contactMedium = ContactMediumMapper.INSTANCE.createContactMediumMapper(request);
        ContactMedium saveContactMedium = contactMediumRepository.save(contactMedium);
        //todo: var olan customerId'ye ait contact medium zaten varsa hata handle edilecek.
        return new CreateContactMediumResponse(
                saveContactMedium.getId(),
                saveContactMedium.getEmail(),
                saveContactMedium.getMobilePhone(),
                saveContactMedium.getHomePhone(),
                saveContactMedium.getFaxNumber(),
                saveContactMedium.getCustomer().getId()
        );
    }

    @Override
    public UpdateContactMediumResponse updateContactMedium(int id ,UpdateContactMediumRequest request) {

        Optional<ContactMedium> contactMediumOptional = contactMediumRepository.findById(id);
        ContactMedium existingMedium = contactMediumOptional.get();
        ContactMedium contactMedium = ContactMediumMapper.INSTANCE.updateContactMedium(request,existingMedium);
        ContactMedium saveContactMedium = contactMediumRepository.save(contactMedium);


        return new UpdateContactMediumResponse(
                saveContactMedium.getId(),
                saveContactMedium.getEmail(),
                saveContactMedium.getMobilePhone(),
                saveContactMedium.getEmail(),
                saveContactMedium.getFaxNumber(),
                saveContactMedium.getCustomer().getId()
        );
    }

    @Override
    public void deleteContactMedium(int id) {
            contactMediumRepository.deleteById(id);
    }

    @Override
    public List<GetAllContactMediumResponse> getAllContactMedium() {
        List<ContactMedium> contactMedia = contactMediumRepository.findAll();

        return ContactMediumMapper.INSTANCE.contactMediumToListContactResponses(contactMedia);
    }

    @Override
    public Optional<GetByIdContactMediumResponse> getByContactMediumId(int id) {
       Optional<ContactMedium> contactMediumOptional = contactMediumRepository.findById(id);

        return contactMediumOptional.map(ContactMediumMapper.INSTANCE::getByIdContactMediumMapper);


    }

    @Override
    public List<ContactMediumWithCustomerResponse> findByCustomerId(int customerId) {
        List<ContactMedium> contactMediums = contactMediumRepository.findByCustomerId(customerId);
        return ContactMediumMapper.INSTANCE.toDtoList(contactMediums);
    }


}
