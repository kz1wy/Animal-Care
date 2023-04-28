package com.animalcare.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequest {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }
    public String getPassword(){
        return password;
    }
}
