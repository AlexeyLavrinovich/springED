package com.aliakseila.springED.service;

import com.aliakseila.springED.event.createPost.CreatePostPublisher;
import com.aliakseila.springED.mapper.PostMapper;
import com.aliakseila.springED.model.dto.PostDto;
import com.aliakseila.springED.model.entity.Post;
import com.aliakseila.springED.model.entity.Profile;
import com.aliakseila.springED.service.repository.PostRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepo postRepo;
    private final CreatePostPublisher publisher;

    public void createPost(Profile profile, Post post){
        publisher.publishCreatePostEvent(post);
    }

    public List<PostDto> getAll() {
        return postRepo.findAll().stream().map(PostMapper.INSTANCE::mapToDto).collect(Collectors.toList());
    }
}
