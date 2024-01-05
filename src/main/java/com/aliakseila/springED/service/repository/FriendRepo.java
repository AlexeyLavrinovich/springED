package com.aliakseila.springED.service.repository;

import com.aliakseila.springED.model.entity.EmbeddedFriendId;
import com.aliakseila.springED.model.entity.Friend;
import com.aliakseila.springED.model.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepo extends JpaRepository<Friend, EmbeddedFriendId> {


    List<Friend> findAllById_OwnerId(Profile ownerId);

}
