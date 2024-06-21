package com.tcellpair3.addressservice.service.concretes;

import com.tcellpair3.addressservice.clients.CustomerServiceClient;
import com.tcellpair3.addressservice.core.dto.requests.CreateAddressRequest;
import com.tcellpair3.addressservice.core.dto.requests.UpdateAddressRequest;
import com.tcellpair3.addressservice.core.dto.responses.*;
import com.tcellpair3.addressservice.core.mappers.AddressMapper;
import com.tcellpair3.addressservice.entities.Address;
import com.tcellpair3.addressservice.repositories.AddressRepository;
import com.tcellpair3.addressservice.service.abstracts.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.turkcell.tcell.exception.exceptions.type.BaseBusinessException;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final CustomerServiceClient customerServiceClient;

    @Override
    public void deleteAddress(int id) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        if (addressOptional.isPresent()) {
            Address address = addressOptional.get();
            int customerId = address.getCustomerId();
            long addressCount = addressRepository.countByCustomerId(customerId);
            if (addressCount <= 1) {
                throw new BaseBusinessException("A customer must have at least one address. You cannot delete the last address.");
            }
            if (address.isDefault()) {
                throw new BaseBusinessException("The address that you want to delete is a default address. Please, change the default address then try again");
            }
            addressRepository.deleteById(id);
        } else {
            throw new BaseBusinessException("Address not found");
        }
    }

    @Override
    public boolean existById(int addressId) {
        return addressRepository.existsById(addressId);
    }

    @Override
    public List<GetAddressByCustomerIdResponse> getAddressesByCustomerId(Integer customerId) {
        boolean customerExists = customerServiceClient.doesCustomerExist(customerId);
        if (!customerExists) {
            throw new BaseBusinessException("Customer does not exist");
        }
        List<Address> addresses = addressRepository.findByCustomerId(customerId);
        return AddressMapper.INSTANCE.getAllAddressByCustomerId(addresses);
    }


    @Override
    public List<GetAllAddressResponse> getAllAddress() {
        List<Address> addresses = addressRepository.findAll();
        return AddressMapper.INSTANCE.addressToListAddressResponse(addresses);
    }

    @Override
    public Optional<GetByAddressIdResponse> getByIdAddress(int id) {
        Optional<Address> existingAddress = addressRepository.findById(id);
        if (existingAddress.isEmpty()) {
            throw new BaseBusinessException("Address not found");
        }
        return existingAddress.map(AddressMapper.INSTANCE::getByAddressIdMapper);
    }

    @Override
    public List<AddressResponse> getAddressesByCustomerInvoiceId(Integer customerInvoiceId) {
        ResponseEntity<GetAddressByCustomerInvoiceIdResponse> responseEntity = customerServiceClient.getCustomerByInvoiceId(customerInvoiceId);

        GetAddressByCustomerInvoiceIdResponse customerResponse = responseEntity.getBody();

        if (customerResponse != null && customerResponse.getId() != null) {

            return addressRepository.findAddressesByCustomerId(customerResponse.getId());
        }
        return Collections.emptyList();
    }

    @Override
    public void setDefaultAddress(int customerId, int defaultAddressId) {
        List<GetAddressByCustomerIdResponse> customerAddressList = getAddressesByCustomerId(customerId);

        for (GetAddressByCustomerIdResponse address : customerAddressList) {
            UpdateAddressRequest request = new UpdateAddressRequest();
            request.setCustomerId(customerId);
            request.setCity(address.getCity());
            request.setAddressDescription(address.getAddressDescription());
            request.setStreet(address.getStreet());
            request.setHouseFlatNumber(address.getHouseFlatNumber());
            request.setDistrict(address.getDistrict());

            request.setDefault(Objects.equals(defaultAddressId, address.getAddressId()));

            updateAddress(address.getAddressId(), request);
        }
    }

    @Override
    public CreateAddressResponse createAddress(CreateAddressRequest request) {
        request.setDefault(false);
        boolean customerExists = customerServiceClient.doesCustomerExist(request.getCustomerId());
        if (!customerExists) {
            throw new BaseBusinessException("Customer does not exist");
        }
        boolean customerHasAddresses = addressRepository.existsByCustomerId(request.getCustomerId());
        if (!customerHasAddresses) {
            request.setDefault(true);
        } else {
            request.setDefault(false);
        }
        Address address = AddressMapper.INSTANCE.createAddressMapper(request);
        Address saveAddress = addressRepository.save(address);
        return new CreateAddressResponse(
                saveAddress.getId(),
                saveAddress.getCustomerId(),
                saveAddress.getCity(),
                saveAddress.getAddressDescription(),
                saveAddress.getStreet(),
                saveAddress.getHouseFlatNumber(),
                saveAddress.getDistrict(),
                saveAddress.isDefault()
        );
    }

    @Override
    public UpdateAddressResponse updateAddress(int id, UpdateAddressRequest request) {
        Optional<Address> addressOptional = addressRepository.findById(id);

        if (addressOptional.isEmpty()) {
            throw new BaseBusinessException("Address not found");
        }

        Address existingAddress = addressOptional.get();

        boolean customerExists = customerServiceClient.doesCustomerExist(request.getCustomerId());
        if (!customerExists) {
            throw new BaseBusinessException("Customer does not exist");
        }

        //address.setAddressId(existingAddress.getAddressId());
        //return addressRepository.saveAndFlush(address);

        Address address = AddressMapper.INSTANCE.updateAddressMapper(request, existingAddress);
        Address saveAddress = addressRepository.save(address);

        return new UpdateAddressResponse(
                saveAddress.getId(),
                saveAddress.getCustomerId(),
                saveAddress.getCity(),
                saveAddress.getAddressDescription(),
                saveAddress.getStreet(),
                saveAddress.getHouseFlatNumber(),
                saveAddress.getDistrict(),
                saveAddress.isDefault()
        );
    }

    @Override
    public AddressResponse getAddressDetails(int id) {
        Address address = addressRepository.findById(id).orElseThrow();
        return new AddressResponse(address.getId(),
                address.getCustomerId(),
                address.getCity(),
                address.getDistrict(),
                address.getStreet(),
                address.getHouseFlatNumber(),
                address.getAddressDescription(),
                address.isDefault());
    }
}
