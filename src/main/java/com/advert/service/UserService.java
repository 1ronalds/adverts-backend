package com.advert.service;

import com.advert.handler.InvalidDataException;
import com.advert.model.UserDto;
import com.advert.repository.UserRepository;
import com.advert.repository.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void createNewUser(UserDto userDto){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        userRepository.save(new UserEntity(null, userDto.getUsername(), userDto.getEmail(), bCryptPasswordEncoder.encode(userDto.getPassword()), userDto.getPhone(), false));
    }

    public void updateUser(String username, UserDto userDto){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Long userId = userRepository.findByUsername(username).get().getUserID();
        userRepository.save(new UserEntity(userId, userDto.getUsername(), userDto.getEmail(), bCryptPasswordEncoder.encode(userDto.getPassword()), userDto.getPhone(), false));
    }

    public void deleteUser(String username, UserDto userDto){
        if(username.equals(userDto.getUsername())) {
            userRepository.deleteByUsername(userDto.getUsername());
        } else {
            throw new InvalidDataException();
        }
    }
}
