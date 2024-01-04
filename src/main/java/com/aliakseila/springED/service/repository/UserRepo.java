package com.aliakseila.springED.service.repository;

import com.aliakseila.springED.model.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    @EntityGraph(value = "user-graph", type = EntityGraph.EntityGraphType.LOAD)
    Optional<User> findByUsername(String username);

}
