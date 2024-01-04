package com.aliakseila.springED.service;

import com.aliakseila.springED.exception.NotFoundException;
import com.aliakseila.springED.mapper.ProfileMapper;
import com.aliakseila.springED.model.dto.ProfileDto;
import com.aliakseila.springED.model.entity.Friend;
import com.aliakseila.springED.model.entity.Profile;
import com.aliakseila.springED.model.entity.EmbeddedFriendId;
import com.aliakseila.springED.model.entity.User;
import com.aliakseila.springED.service.repository.FriendRepo;
import com.aliakseila.springED.service.repository.ProfileRepo;
import com.aliakseila.springED.service.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProfileService {

    private final UserRepo userRepo;
    private final ProfileRepo profileRepo;
    private final FriendRepo friendRepo;

    public Profile findProfileByUsername(String username) {
        return userRepo.findByUsername(username)
                .map(User::getProfile)
                .orElseThrow(() -> new NotFoundException(String.format("Profile with username \"%s\" not found", username)));
    }

    public Profile findById(Long id) throws NotFoundException{
        return profileRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Profile with id \"%d\" not found", id)));
    }

    public void addFriend(Profile profile, String friendUsername) {
        Profile friendProfile = userRepo.findByUsername(friendUsername)
                .map(User::getProfile)
                .filter(u -> !u.equals(profile))
                .orElseThrow(() -> new NotFoundException(String.format("Profile with username \"%s\" not found", friendUsername)));
        Friend friend = new Friend(new EmbeddedFriendId(profile.getId(), friendProfile.getId()), profile, friendProfile);
        friendRepo.save(friend);
        saveNewFriendToProfile(profile,friend);
        friend = new Friend(new EmbeddedFriendId(friendProfile.getId(), profile.getId()), friendProfile, profile);
        friendRepo.save(friend);
        saveNewFriendToProfile(friendProfile, friend);
    }

    private void saveNewFriendToProfile(Profile profile, Friend friend){
        List<Friend> friends = profile.getFriends();
        friends.add(friend);
        profile.setFriends(friends);
        profileRepo.save(profile);
    }

    public List<ProfileDto> getFriends(Profile profile) {
        return profileRepo.findById(profile.getId())
                .map(p -> p.getFriends()
                        .stream()
                        .map(Friend::getOwner)
                        .map(ProfileMapper.INSTANCE::mapToDto)
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new NotFoundException(String.format("Profile with username \"%s\" has no friends", profile.getUsername())));
    }
}
