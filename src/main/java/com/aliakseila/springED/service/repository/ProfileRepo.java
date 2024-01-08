package com.aliakseila.springED.service.repository;

import com.aliakseila.springED.model.entity.Profile;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepo extends JpaRepository<Profile, Long> {

    @Override
    @EntityGraph(value = "profile-with-posts-graph", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Profile> findById(Long id);

    @Override
    @EntityGraph(value = "profile-with-posts-graph", type = EntityGraph.EntityGraphType.LOAD)
    List<Profile> findAll();
}
