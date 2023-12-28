package com.aliakseila.springED.controller;

import com.aliakseila.springED.mapper.ProfileMapper;
import com.aliakseila.springED.model.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfilePageController {

    @GetMapping
    public ResponseEntity getProfile(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(ProfileMapper.INSTANCE.mapToDto(user.getProfile()));
    }


}
