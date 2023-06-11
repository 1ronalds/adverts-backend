package com.advert.controller;


import com.advert.model.AuthenticationDto;
import com.advert.model.AuthenticationTokenDto;
import com.advert.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<AuthenticationTokenDto> authenticateUser(@RequestBody AuthenticationDto authenticationDto){
        return ResponseEntity.ok(authenticationService.authenticate(authenticationDto));
    }
}
