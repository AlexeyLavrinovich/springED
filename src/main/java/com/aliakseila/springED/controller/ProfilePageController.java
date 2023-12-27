package com.aliakseila.springED.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfilePageController {

    @GetMapping
    public ResponseEntity getProfile(){
        return ResponseEntity.ok("");
    }


}
