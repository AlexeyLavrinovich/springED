package com.aliakseila.springED.service;

import com.aliakseila.springED.exception.NotFoundException;
import com.aliakseila.springED.mapper.PostMapper;
import com.aliakseila.springED.mapper.ProfileMapper;
import com.aliakseila.springED.model.dto.ProfileDto;
import com.aliakseila.springED.model.dto.ProfileWithPostsDto;
import com.aliakseila.springED.model.entity.EmbeddedFriendId;
import com.aliakseila.springED.model.entity.Friend;
import com.aliakseila.springED.model.entity.Profile;
import com.aliakseila.springED.model.entity.User;
import com.aliakseila.springED.service.repository.FriendRepo;
import com.aliakseila.springED.service.repository.ProfileRepo;
import com.aliakseila.springED.service.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public Profile findById(Long id) throws NotFoundException {
        return profileRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Profile with id \"%d\" not found", id)));
    }

    public void addFriend(Profile ownerProfile, String friendUsername) {
        Profile friendProfile = userRepo.findByUsername(friendUsername)
                .map(User::getProfile)
                .filter(u -> !u.equals(ownerProfile))
                .orElseThrow(() -> new NotFoundException(String.format("Profile with username \"%s\" not found", friendUsername)));
        saveNewFriendToProfile(ownerProfile, friendProfile);
        saveNewFriendToProfile(friendProfile, ownerProfile);
    }

    private Friend saveFriend(Profile ownerProfile, Profile friendProfile) {
        Friend friend = new Friend(new EmbeddedFriendId(ownerProfile.getId(), friendProfile.getId()), ownerProfile, friendProfile);
        return friendRepo.save(friend);
    }

    private void saveNewFriendToProfile(Profile ownerProfile, Profile friendProfile) {
        Friend friend = saveFriend(ownerProfile, friendProfile);
        Set<Friend> friends = ownerProfile.getFriends();
        friends.add(friend);
        ownerProfile.setFriends(friends);
        profileRepo.save(ownerProfile);
    }

    public List<ProfileDto> getFriends(User user) {
        return Optional.of(user.getProfile())
                .map(p -> p.getFriends()
                        .stream()
                        .map(Friend::getFriend)
                        .map(ProfileMapper.INSTANCE::mapToDto)
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new NotFoundException(String.format("Profile with username \"%s\" has no friends", user.getUsername())));
    }

    public ProfileWithPostsDto getProfileWithPosts(Profile authProfile, String username) {
        Profile userProfile = userRepo.findByUsername(username)
                .map(User::getProfile)
                .orElseThrow(() -> new NotFoundException(String.format("User with username \"%s\" not found", username)));
        if(authProfile.equals(userProfile)){
            return getProfileWithPosts(authProfile);
        }
        return authProfile.getFriends()
                .stream()
                .map(Friend::getFriend)
                .filter(friendProfile -> friendProfile.equals(userProfile))
                .findFirst()
                .map(this::getProfileWithPosts)
                .orElse(getProfileWithPosts(ProfileMapper.INSTANCE.mapToDto(userProfile)));
    }

    public ProfileWithPostsDto getProfileWithPosts(Profile profile){
        ProfileWithPostsDto profileWithPostsDto = new ProfileWithPostsDto();
        profileWithPostsDto.setProfileDto(ProfileMapper.INSTANCE.mapToDto(profile));
        profileWithPostsDto.setPostDtoList(
                profile.getPosts()
                        .stream()
                        .map(PostMapper.INSTANCE::mapToDto)
                        .collect(Collectors.toList())
        );
        return profileWithPostsDto;
    }
    private ProfileWithPostsDto getProfileWithPosts(ProfileDto profileDto){
        ProfileWithPostsDto profileWithPostsDto = new ProfileWithPostsDto();
        profileWithPostsDto.setProfileDto(profileDto);
        return profileWithPostsDto;
    }

}
