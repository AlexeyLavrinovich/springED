package com.aliakseila.springED.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileDto implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
}
