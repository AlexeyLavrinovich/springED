package com.aliakseila.springED.service;

import com.aliakseila.springED.exception.NotFoundException;
import com.aliakseila.springED.model.entity.Profile;
import com.aliakseila.springED.model.entity.User;
import com.aliakseila.springED.service.repository.ProfileRepo;
import com.aliakseila.springED.service.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProfileService {

    private final UserRepo userRepo;
    private final ProfileRepo profileRepo;

    public Profile findProfileByUsername(String username) {
        return userRepo.findByUsername(username)
                .map(User::getProfile)
                .orElseThrow(() -> new NotFoundException(String.format("Profile with username \"%s\" not found", username)));
    }

    public Profile findById(Long id) throws NotFoundException{
        return profileRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Profile with id \"%d\" not found", id)));
    }
}
