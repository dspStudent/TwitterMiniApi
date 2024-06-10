package com.twitter.mini.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModal {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private String confirmPassword;
}
