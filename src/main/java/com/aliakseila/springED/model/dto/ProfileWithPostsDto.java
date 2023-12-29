package com.aliakseila.springED.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfileWithPostsDto {

    private ProfileDto profileDto;
    private List<PostDto> postDtoList;

}
