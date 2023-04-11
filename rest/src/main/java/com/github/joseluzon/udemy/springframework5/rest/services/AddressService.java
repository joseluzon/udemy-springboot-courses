package com.github.joseluzon.udemy.springframework5.rest.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.joseluzon.udemy.springframework5.rest.entities.Address;
import com.github.joseluzon.udemy.springframework5.rest.repositories.AddressRepository;
import com.github.joseluzon.udemy.springframework5.rest.repositories.ProfilesRepository;

@Service
public class AddressService {
    
    private AddressRepository addressRepository;

    @Autowired
    public AddressService(final AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAddressByUserIdAndProfileId(Integer userId, Integer profileId) {
        return addressRepository.findByUserIdAndProfileId(userId, profileId);
    }
}
