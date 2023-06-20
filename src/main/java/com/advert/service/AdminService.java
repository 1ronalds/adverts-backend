package com.advert.service;

import java.util.ArrayList;

import com.advert.repository.UserRepository;
import com.advert.repository.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.advert.model.UserDto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@RequiredArgsConstructor
@Service
public class AdminService {
    private final UserRepository userRepository;

    public ArrayList<String> viewAdminUsers(){
        return userRepository.findByIsAdmin(true);
    }

    public void deleteAdminUser(String username){
        if(userRepository.findByUsername(username).get().isAdmin()){
            userRepository.deleteByUsername(username);
        } else {
            throw new RuntimeException("You are not deleting admin user!");
        }

    }

    public void createAdminUser(UserDto userDto){
        if(userRepository.findByUsername(userDto.getUsername()).isEmpty()){
            userRepository.save(new UserEntity(null, userDto.getUsername(), userDto.getEmail(), userDto.getPassword(), userDto.getPhone(), true));
        } else {
            throw new RuntimeException("User already exists!");
        }
    }

    public boolean isAdmin(String username){
        return userRepository.findByUsername(username).get().isAdmin();
    }
}