package com.advert.service;

import com.advert.handler.InvalidUsernamePasswordException;
import com.advert.model.AuthenticationDto;
import com.advert.model.AuthenticationTokenDto;
import com.advert.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    @Value("${jwt.secret-key}")
    private String secret;

    @Value("${jwt.expiration-milliseconds}")
    private Integer expiration;

    public AuthenticationTokenDto authenticate(AuthenticationDto authenticationDto){
        AuthenticationTokenDto authenticationTokenDto = new AuthenticationTokenDto();
        if(checkCredentials(authenticationDto)){
            authenticationTokenDto.setAuthorization(
                    "Bearer " + Jwts.builder()
                    .setSubject(authenticationDto.getUsername())
                    .claim("role", isAdmin(authenticationDto) ? "admin" : "user")
                    .setExpiration(new Date(System.currentTimeMillis() + expiration))
                    .signWith(SignatureAlgorithm.HS256, secret)
                    .compact()
            );
        } else {
            throw new InvalidUsernamePasswordException();
        }
        return authenticationTokenDto;
    }

    private Boolean checkCredentials(AuthenticationDto authenticationDto) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String passwordHash = userRepository.findByUsername(authenticationDto.getUsername())
                .orElseThrow(InvalidUsernamePasswordException::new).getPassword();
        return bCryptPasswordEncoder.matches(authenticationDto.getPassword(), passwordHash);
    }

    private Boolean isAdmin(AuthenticationDto authenticationDto){
        return userRepository.findByUsername(authenticationDto.getUsername()).get().isAdmin();
    }

}
