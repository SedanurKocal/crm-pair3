package com.tcellpair3.customerservice.service.concretes;

import com.tcellpair3.customerservice.core.dtos.requests.address.CreateAddressRequest;
import com.tcellpair3.customerservice.core.dtos.requests.address.UpdateAddressRequest;
import com.tcellpair3.customerservice.core.dtos.responses.address.CreateAddressResponse;
import com.tcellpair3.customerservice.core.dtos.responses.address.GetAllAddressResponse;
import com.tcellpair3.customerservice.core.dtos.responses.address.GetByIdAddressResponse;
import com.tcellpair3.customerservice.core.dtos.responses.address.UpdateAddressResponse;
import com.tcellpair3.customerservice.core.mappers.AddressMapper;
import com.tcellpair3.customerservice.entities.Address;
import com.tcellpair3.customerservice.repositories.AddressRepository;
import com.tcellpair3.customerservice.service.abstracts.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    @Override
    public CreateAddressResponse createAddress(CreateAddressRequest request) {
        Address address= AddressMapper.INSTANCE.createAddressMapper(request);
        Address saveAddress= addressRepository.save(address);

        return new CreateAddressResponse(
                saveAddress.getId(),
                saveAddress.getCity(),
                saveAddress.getDistrict(),
                saveAddress.getStreet(),
                saveAddress.getHouseFlatNumber(),
                saveAddress.getAddressDescription(),
                saveAddress.getCustomer().getId()

        );
    }

    @Override
    public UpdateAddressResponse updateAddress(int id, UpdateAddressRequest request) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        Address existingAddress = addressOptional.get();
        Address address= AddressMapper.INSTANCE.updateAddress(request,existingAddress);
        Address saveAddress= addressRepository.save(address);

        return new UpdateAddressResponse(
                saveAddress.getId(),
                saveAddress.getCity(),
                saveAddress.getDistrict(),
                saveAddress.getStreet(),
                saveAddress.getHouseFlatNumber(),
                saveAddress.getAddressDescription(),
                saveAddress.getCustomer().getId()

        );

    }

    @Override
    public void deleteAddress(int id) {
        addressRepository.deleteById(id);

    }

    @Override
    public List<GetAllAddressResponse> getAllAddresses() {
        List<Address> addressList = addressRepository.findAll();
        return AddressMapper.INSTANCE.AddressToListAddressResponses(addressList);
    }

    @Override
    public Optional<GetByIdAddressResponse> getByIdAddress(int id) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        return addressOptional.map(AddressMapper.INSTANCE::getByIdAddressMapper);
    }
}
