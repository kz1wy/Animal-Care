package com.animalcare.dto;

import com.animalcare.model.Role;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private Role role;

}
