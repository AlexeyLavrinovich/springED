package com.aliakseila.springED.controller;

import com.aliakseila.springED.event.RegisterUserPublisher;
import com.aliakseila.springED.mapper.RegistrationMapper;
import com.aliakseila.springED.model.dto.RegistrationDto;
import com.aliakseila.springED.model.entity.Profile;
import com.aliakseila.springED.model.entity.User;
import com.aliakseila.springED.service.UserService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
@AllArgsConstructor
public class RegistrationController {

    private final UserService userService;
    private final RegisterUserPublisher publisher;

    @GetMapping
    public ResponseEntity info(){
        return ResponseEntity.ok("Set your full name and password");
    }

    @SneakyThrows
    @PostMapping("/new")
    public ResponseEntity createUser(@RequestBody RegistrationDto registrationDto) {
        User newUser = RegistrationMapper.INSTANCE.mapToUser(registrationDto);
        Profile newProfile = RegistrationMapper.INSTANCE.mapToProfile(registrationDto);
        userService.createUser(newUser);
        publisher.publishUserCreateEvent(newUser, newProfile);
        return ResponseEntity.ok("Registration successfully finished!");
    }

}
