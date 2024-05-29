package com.tcellpair3.addressservice.service.concretes;

import com.tcellpair3.addressservice.clients.CustomerServiceClient;
import com.tcellpair3.addressservice.core.dto.responses.AddressDtoResponse;
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
    public List<Address> getAddressesByCustomerId(Integer customerId) {
        return addressRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    @Override
    public Optional<Address> getByIdAddress(int id) {
        return addressRepository.findById(id);
    }

    @Override
    public AddressDtoResponse createAddress(AddressDtoResponse request) {
        boolean customerExists = client.doesCustomerExist(request.getCustomerId());
        if (!customerExists) {
            throw new BusinessException("Customer does not exist");
        }

        Address address = AddressMapper.INSTANCE.addressDTOToAddress(request);
        Address savedAddress = addressRepository.save(address);
        return AddressMapper.INSTANCE.addressToAddressDTO(savedAddress);

    }

    @Override
    public Address updateAddress(int id,Address address) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        if (!addressOptional.isPresent()) {
            throw new BusinessException("Address not found");
        }

        Address existingAddress = addressOptional.get();

        boolean customerExists = client.doesCustomerExist(address.getCustomerId());
        if (!customerExists) {
            throw new BusinessException("Customer does not exist");
        }

        // Set the ID of the updatedAddress to the existing ID
        address.setAddressId(existingAddress.getAddressId());

        // Save the updated address, which should now update the existing record
        return addressRepository.saveAndFlush(address);
    }
}
