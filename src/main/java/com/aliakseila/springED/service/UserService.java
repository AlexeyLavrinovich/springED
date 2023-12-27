package com.aliakseila.springED.service;

import com.aliakseila.springED.exception.AlreadyExistException;
import com.aliakseila.springED.exception.NotFoundException;
import com.aliakseila.springED.mapper.UserMapper;
import com.aliakseila.springED.model.dto.UserDto;
import com.aliakseila.springED.model.entity.User;
import com.aliakseila.springED.service.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;

    public List<UserDto> getAllUsers() {
        return userRepo.findAll().stream()
                .map(UserMapper.INSTANCE::mapToDto)
                .collect(Collectors.toList());
    }

    public UserDto findById(Long id) throws NotFoundException {
        return userRepo.findById(id)
                .map(UserMapper.INSTANCE::mapToDto)
                .orElseThrow(() -> new NotFoundException(String.format("User with id \"%d\" not found", id)));
    }

    public List<UserDto> findByUsername(String username) throws NotFoundException {
        return userRepo.findByUsername(username)
                .map(UserMapper.INSTANCE::mapToDto)
                .map(List::of)
                .orElseThrow(() -> new NotFoundException(String.format("User with username \"%s\" not found", username)));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

    }

    public void createUser(User newUser) throws AlreadyExistException {
        Optional<User> user = userRepo.findByUsername(newUser.getUsername());
        if (user.isEmpty()){
            userRepo.save(newUser);
        } else {
            throw new AlreadyExistException(String.format("User with username \"%s\" already exists", user.get().getUsername()));
        }
    }

}
