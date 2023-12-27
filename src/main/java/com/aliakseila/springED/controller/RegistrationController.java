package com.aliakseila.springED.controller;

import com.aliakseila.springED.mapper.UserMapper;
import com.aliakseila.springED.model.dto.UserDto;
import com.aliakseila.springED.model.entity.User;
import com.aliakseila.springED.event.RegisterUserPublisher;
import com.aliakseila.springED.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;
    @Autowired
    private RegisterUserPublisher publisher;

    @GetMapping
    public ResponseEntity info(){
        return ResponseEntity.ok("Set your full name and password");
    }

    @SneakyThrows
    @PostMapping("/new")
    public ResponseEntity createUser(@RequestBody UserDto user) {
        User newUser = UserMapper.INSTANCE.mapToEntity(user);
        userService.createUser(newUser);
        publisher.publishUserCreateEvent(newUser);
        return ResponseEntity.ok("Registration successfully finished!");
    }

}
