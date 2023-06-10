package com.advert.service;

import com.advert.model.UserDto;
import com.advert.repository.UserRepository;
import com.advert.repository.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void createNewUser(UserDto userDto){
        userRepository.save(new UserEntity(null, userDto.getUsername(), userDto.getEmail(), userDto.getPassword(), userDto.getPhone(), false));
    }

    public void updateUser(String username, UserDto userDto){
        Long userId = userRepository.findByUsername(username).getUserID();
        userRepository.save(new UserEntity(userId, userDto.getUsername(), userDto.getEmail(), userDto.getPassword(), userDto.getPhone(), false));
    }

    public void deleteUser(UserDto userDto){
        userRepository.deleteByUsername(userDto.getUsername());

    }
}
