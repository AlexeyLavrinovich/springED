package com.aliakseila.springED.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationDto {

    private String username;
    private String password;
    private String name;
    private String surname;
    private Integer age;
}
