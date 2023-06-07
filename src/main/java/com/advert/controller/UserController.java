package com.advert.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.advert.model.UserDto;
import com.advert.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> createNewUser(@RequestBody UserDto userDto){
        userService.createNewUser(userDto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{username}")
    public ResponseEntity<Void> updateUser(@PathVariable String username, @RequestBody UserDto userDto){
        userService.updateUser(username, userDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestBody UserDto userDto){
        userService.deleteUser(userDto);
        return ResponseEntity.noContent().build();
    }
}
