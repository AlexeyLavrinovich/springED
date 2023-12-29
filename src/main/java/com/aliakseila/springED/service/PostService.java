package com.aliakseila.springED.service;

import com.aliakseila.springED.mapper.PostMapper;
import com.aliakseila.springED.model.dto.PostDto;
import com.aliakseila.springED.model.entity.Post;
import com.aliakseila.springED.model.entity.Profile;
import com.aliakseila.springED.service.repository.PostRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepo postRepo;

    public void createPost(Profile profile, Post post){
        post.setCreatedAt(Date.valueOf(LocalDate.now()));
        post.setModifiedAt(Date.valueOf(LocalDate.now()));
        post.setAuthor(profile);
        postRepo.save(post);
    }

    public List<PostDto> getAll() {
        return postRepo.findAll().stream().map(PostMapper.INSTANCE::mapToDto).collect(Collectors.toList());
    }
}
