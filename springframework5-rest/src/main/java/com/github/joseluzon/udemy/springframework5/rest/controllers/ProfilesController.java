package com.github.joseluzon.udemy.springframework5.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.joseluzon.udemy.springframework5.rest.entities.Profile;
import com.github.joseluzon.udemy.springframework5.rest.services.ProfilesService;

@RestController
@RequestMapping("/users/{userId}/profiles")
public class ProfilesController {

    private ProfilesService profilesService;

    @Autowired
    public ProfilesController(final ProfilesService profilesService) {
        this.profilesService = profilesService;
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<Profile> getProfileById(@PathVariable("userId") final Integer userId, @PathVariable("profileId") final Integer profileId) {
        return new ResponseEntity<Profile>(profilesService.getProfileById(userId, profileId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Profile> createProfile(@PathVariable("userId") final Integer userId, @RequestBody final Profile profile) {
        return new ResponseEntity<Profile>(profilesService.createProfile(userId, profile), HttpStatus.OK);
    }
    
}
