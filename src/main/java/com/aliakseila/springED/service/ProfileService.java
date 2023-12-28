package com.aliakseila.springED.service;

import com.aliakseila.springED.exception.NotFoundException;
import com.aliakseila.springED.mapper.ProfileMapper;
import com.aliakseila.springED.model.dto.ProfileDto;
import com.aliakseila.springED.model.entity.Profile;
import com.aliakseila.springED.model.entity.User;
import com.aliakseila.springED.service.repository.ProfileRepo;
import com.aliakseila.springED.service.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProfileService {

    private final UserRepo userRepo;
    private final ProfileRepo profileRepo;

    @Cacheable("profiles")
    public ProfileDto findProfileByUsername(String username) {
        return userRepo.findByUsername(username)
                .map(User::getProfile)
                .map(ProfileMapper.INSTANCE::mapToDto)
                .orElseThrow(() -> new NotFoundException(String.format("User with username \"%s\" not found", username)));
    }

    public Profile findById(Long id) throws NotFoundException{
        return profileRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User with id \"%d\" not found", id)));
    }
}
