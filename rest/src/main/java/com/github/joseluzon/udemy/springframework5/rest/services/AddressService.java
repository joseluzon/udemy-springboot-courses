package com.github.joseluzon.udemy.springframework5.rest.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.github.joseluzon.udemy.springframework5.rest.entities.Address;
import com.github.joseluzon.udemy.springframework5.rest.repositories.AddressRepository;
import com.github.joseluzon.udemy.springframework5.rest.repositories.ProfilesRepository;

@Service
public class AddressService {
    
    private AddressRepository addressRepository;

    private ProfilesRepository profilesRepository;

    @Autowired
    public AddressService(final AddressRepository addressRepository, final ProfilesRepository profilesRepository) {
        this.addressRepository = addressRepository;
        this.profilesRepository = profilesRepository;
    }

    public List<Address> getAddressByUserIdAndProfileId(Integer userId, Integer profileId) {
        return addressRepository.findByUserIdAndProfileId(userId, profileId);
    }

    public Address createAddress(final Integer userId, final Integer profileId, final Address address) {
        final var profile = profilesRepository.findByUserIdAndProfileId(userId, profileId);
        if (profile.isPresent()) {
            address.setProfile(profile.get());
            return addressRepository.save(address);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Profile id %d and user id % not found", profileId, userId));
    }
}
