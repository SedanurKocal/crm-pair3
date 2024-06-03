package com.tcellpair3.addressservice.service.concretes;

import com.tcellpair3.addressservice.clients.CustomerServiceClient;
import com.tcellpair3.addressservice.core.dto.requests.CreateAddressRequest;
import com.tcellpair3.addressservice.core.dto.requests.UpdateAddressRequest;
import com.tcellpair3.addressservice.core.dto.responses.*;
import com.tcellpair3.addressservice.core.exception.type.BusinessException;
import com.tcellpair3.addressservice.core.mappers.AddressMapper;
import com.tcellpair3.addressservice.entities.Address;
import com.tcellpair3.addressservice.repositories.AddressRepository;
import com.tcellpair3.addressservice.service.abstracts.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final CustomerServiceClient client;

    @Override
    public void deleteAddress(int id) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        if (addressOptional.isPresent()) {
            Address address = addressOptional.get();
            int customerId = address.getCustomerId();
            long addressCount = addressRepository.countByCustomerId(customerId);
            if(addressCount<=1)
            {
                throw new BusinessException("A customer must have at least one address. You cannot delete the last address.");

            }
            if (address.isDefault()) {
                throw new BusinessException("The address that you want to delete is a default address. Please, change the default address then try again");
            }
            addressRepository.deleteById(id);
        } else {
            throw new BusinessException("Address not found");
        }

    }

    @Override
    public List<GetAddressCustomerById> getAddressesByCustomerId(Integer customerId) {
        boolean customerExists = client.doesCustomerExist(customerId);
        if (!customerExists) {
            throw new BusinessException("Customer does not exist");
        }
        List<Address> addresses = addressRepository.findByCustomerId(customerId);
        return AddressMapper.INSTANCE.getAllAddressByCustomerId(addresses);
    }

    @Override
    public List<GetAllAddressResponse> getAllAddress() {
        List<Address> addresses=addressRepository.findAll();
        return AddressMapper.INSTANCE.addressToListAddressResponse(addresses);
    }

    @Override
    public Optional<GetByAddressIdResponse> getByIdAddress(int id) {
        Optional<Address> existingAddress = addressRepository.findById(id);
        if(existingAddress.isEmpty())
        {
            throw new BusinessException("Address not found");
        }
        return existingAddress.map(AddressMapper.INSTANCE::getByAddressIdMapper);
    }

    @Override
    public CreateAddressResponse createAddress(CreateAddressRequest request) {
        boolean customerExists = client.doesCustomerExist(request.getCustomerId());
        if (!customerExists) {
            throw new BusinessException("Customer does not exist");
        }

        Address address = AddressMapper.INSTANCE.createAddressMapper(request);
        Address saveAddress = addressRepository.save(address);
        //return AddressMapper.INSTANCE.createAddressResponse(savedAddress);
        return new CreateAddressResponse(
                saveAddress.getAddressId(),
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
        if (!addressOptional.isPresent()) {
            throw new BusinessException("Address not found");
        }

        Address existingAddress = addressOptional.get();

        boolean customerExists = client.doesCustomerExist(request.getCustomerId());
        if (!customerExists) {
            throw new BusinessException("Customer does not exist");
        }

        //address.setAddressId(existingAddress.getAddressId());
        //return addressRepository.saveAndFlush(address);

        Address address = AddressMapper.INSTANCE.updateAddressMapper(request,existingAddress);
        Address saveAddress=addressRepository.save(address);

        return new UpdateAddressResponse(
                saveAddress.getAddressId(),
                saveAddress.getCustomerId(),
                saveAddress.getCity(),
                saveAddress.getAddressDescription(),
                saveAddress.getStreet(),
                saveAddress.getHouseFlatNumber(),
                saveAddress.getDistrict(),
                saveAddress.isDefault()
        );
    }
}
