package com.advert.model;

import lombok.Data;

@Data
public class UserDto {
    private Long userID;
    private String username;
    private String email;
    private String password;
    private String phone;
    private boolean isAdmin;
}
