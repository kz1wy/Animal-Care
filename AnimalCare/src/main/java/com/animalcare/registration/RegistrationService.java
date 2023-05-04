package com.animalcare.registration;

import com.animalcare.dto.RegistrationRequest;
import com.animalcare.model.User;
import com.animalcare.registration.token.ConfirmationToken;
import com.animalcare.registration.token.ConfirmationTokenService;
import com.animalcare.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final UserService userService;
    private final EmailValidator emailValidator;

    private final ConfirmationTokenService confirmationTokenService;
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

//    @Transactional
//    public String confirmToken(String token){
//        ConfirmationToken confirmationToken = confirmationTokenService
//                .getToken(token)
//                .orElset
//    }
}
