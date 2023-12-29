package com.aliakseila.springED.mapper;

import com.aliakseila.springED.model.dto.PostDto;
import com.aliakseila.springED.model.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = ProfileMapper.class)
public abstract class PostMapper {

    public static final PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    public abstract Post mapToEntity(PostDto postDto);

    public abstract PostDto mapToDto(Post post);

}
