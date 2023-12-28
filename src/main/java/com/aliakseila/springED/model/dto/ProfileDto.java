package com.aliakseila.springED.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProfileDto {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private Integer age;
}
