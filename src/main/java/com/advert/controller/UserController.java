package com.advert.controller;

import com.advert.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.advert.model.UserDto;

import lombok.RequiredArgsConstructor;

import javax.validation.Valid;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> createNewUser(@RequestBody UserDto userDto){
        userService.createNewUser(userDto);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("(#username == authentication.principal)")
    @PutMapping("/{username}")
    public ResponseEntity<Void> updateUser(@PathVariable String username, @Valid @RequestBody UserDto userDto){
        userService.updateUser(username, userDto);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("(#username == authentication.principal)")
    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@RequestParam String username, @Valid @RequestBody UserDto userDto){
        userService.deleteUser(username, userDto);
        return ResponseEntity.noContent().build();
    }
}
