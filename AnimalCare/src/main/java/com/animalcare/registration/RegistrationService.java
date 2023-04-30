package com.animalcare.registration;

import com.animalcare.dto.RegistrationRequest;
import com.animalcare.model.User;
import com.animalcare.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final UserService userService;
    private final EmailValidator emailValidator;
    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail){
            throw new IllegalStateException("email is not valid");
        }
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new IllegalStateException("passwords do not match");
        }
        return userService.singUpUser(
                new User(
                        request.getUsername(),
                        request.getPassword(),
                        request.getEmail(),
                        request.getRole(),
                        request.getCreatedAt()
                )
        );
    }
}
