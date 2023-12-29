package com.aliakseila.springED.service.repository;

import com.aliakseila.springED.model.entity.Post;
import com.aliakseila.springED.model.entity.Profile;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {

    @Override
    @EntityGraph(value = "post-graph")
    List<Post> findAll();

}
