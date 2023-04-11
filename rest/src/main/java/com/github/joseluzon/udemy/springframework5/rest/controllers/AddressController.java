package com.github.joseluzon.udemy.springframework5.rest.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.joseluzon.udemy.springframework5.rest.entities.Address;
import com.github.joseluzon.udemy.springframework5.rest.services.AddressService;

@RestController
@RequestMapping("/users/{userId}/profiles/{profileId}/addresses")
public class AddressController {
    
    private AddressService addressService;

    @Autowired
    public AddressController(final AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<List<Address>> getAddressByUserIdAndProfileId(
        @PathVariable("userId") final Integer userId,
        @PathVariable("profileId") final Integer profileId
    ) {
        return new ResponseEntity<>(addressService.getAddressByUserIdAndProfileId(userId, profileId), HttpStatus.OK);
    }
}
