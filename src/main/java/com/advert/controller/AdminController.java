package com.advert.controller;

import java.util.ArrayList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.advert.model.UserDto;
import com.advert.service.AdminService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    
    @GetMapping("/admin-users")
    public ResponseEntity<ArrayList<String>> findAllUsernames(){
        return ResponseEntity.ok(adminService.viewAdminUsers());
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteAdminUser(@PathVariable String username){
        adminService.deleteAdminUser(username);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/new")
    public ResponseEntity<Void> createAdminUser(@RequestBody UserDto userDto){
        adminService.createAdminUser(userDto);
        return ResponseEntity.noContent().build();
    }


}
