package com.advert.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserDto {

    private Long userID;

    @NotEmpty
    @Size(max = 45)
    private String username;

    @NotEmpty
    @Email
    @Size(max = 50)
    private String email;

    @NotEmpty
    @Size(max = 20)
    private String password;

    @NotEmpty
    @Size(max = 12)
    private String phone;


    private boolean isAdmin;
}
