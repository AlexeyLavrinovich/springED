package com.aliakseila.springED.controller;

import com.aliakseila.springED.mapper.ProfileMapper;
import com.aliakseila.springED.model.entity.User;
import com.aliakseila.springED.service.ProfileService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfilePageController {

    @Autowired
    private ProfileService profileService;

    @GetMapping
    public ResponseEntity getProfile(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(ProfileMapper.INSTANCE.mapToDto(user.getProfile()));
    }

    @GetMapping("/search")
    public ResponseEntity lookAtUserProfile(@RequestBody User user){
        return ResponseEntity.ok(profileService.findProfileByUsername(user.getUsername()));
    }


}
