package com.github.joseluzon.udemy.springframework5.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.github.joseluzon.udemy.springframework5.rest.entities.Profile;
import com.github.joseluzon.udemy.springframework5.rest.repositories.ProfilesRepository;
import com.github.joseluzon.udemy.springframework5.rest.repositories.UsersRepository;

@Service
public class ProfilesService {

    private ProfilesRepository profilesRepository;
    private UsersRepository usersRepository;

    @Autowired
    public ProfilesService(final ProfilesRepository profilesRepository, final UsersRepository usersRepository) {
        this.profilesRepository = profilesRepository;
        this.usersRepository = usersRepository;
    }

    public Profile createProfile(final Integer userId, final Profile profile) {
        final var user = usersRepository.findById(userId);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User id %d not found", userId));
        }
        profile.setUser(user.get());
        return profilesRepository.save(profile);
    }

    public Profile getProfileById(final Integer userId, final Integer profileId) {
        return profilesRepository.findByUserIdAndProfileId(userId, profileId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Profile not found for id %d and user id %d", profileId, userId)));
    }

}
