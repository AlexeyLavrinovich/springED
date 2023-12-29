package com.aliakseila.springED.controller;

import com.aliakseila.springED.event.cacheProfile.CacheProfilePublisher;
import com.aliakseila.springED.mapper.ProfileMapper;
import com.aliakseila.springED.model.dto.ProfileDto;
import com.aliakseila.springED.model.entity.User;
import com.aliakseila.springED.service.ProfileService;
import com.hazelcast.core.HazelcastInstance;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/profile")
@AllArgsConstructor
public class ProfilePageController {

    private final ProfileService profileService;
    private final CacheProfilePublisher publisher;

    @Autowired
    private HazelcastInstance hazelcastInstance;

    @GetMapping
    public ResponseEntity getProfile(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(ProfileMapper.INSTANCE.mapToDto(user.getProfile()));
    }

    @GetMapping("/search")
    public ResponseEntity lookAtUserProfile(@RequestBody User user){
        ProfileDto profileDto = (ProfileDto) hazelcastInstance.getMap("Profiles").get(user.getUsername());
        if(Objects.isNull(profileDto)){
            profileDto = profileService.findProfileByUsername(user.getUsername());
            publisher.publishCacheProfileEvent(user.getUsername(), profileDto);
        }
        return ResponseEntity.ok(profileDto);
    }


}
