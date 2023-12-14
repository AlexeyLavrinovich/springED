package com.aliakseila.springED.service;

import com.aliakseila.springED.entity.User;
import com.aliakseila.springED.exception.NotFoundException;
import com.aliakseila.springED.mapper.UserMapper;
import com.aliakseila.springED.dto.UserDto;
import com.aliakseila.springED.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    public List<UserDto> getAllUsers() {
        return userRepo.findAll().stream().map(UserMapper.INSTANCE::mapToDto).collect(Collectors.toList());
    }

    public List<UserDto> findById(Long id) throws NotFoundException {
        Optional<User> user = userRepo.findById(id);
        if (user.isEmpty()){
            throw new NotFoundException(String.format("User with id \"%d\" not found", id));
        }
        return user.stream().map(UserMapper.INSTANCE::mapToDto).collect(Collectors.toList());
    }

    public List<UserDto> findByUsername(String username) throws NotFoundException {
        Optional<User> user = userRepo.findByUsername(username);
        if (user.isEmpty()){
            throw new NotFoundException(String.format("User with username \"%s\" not found", username));
        }
        return user.stream().map(UserMapper.INSTANCE::mapToDto).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUsername(username);
        if (user.isEmpty()){
            throw new UsernameNotFoundException(username);
        }
        return user.get();
    }
}
