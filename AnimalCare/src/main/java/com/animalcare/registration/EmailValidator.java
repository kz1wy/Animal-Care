package com.animalcare.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
@Service
public class EmailValidator implements Predicate<String> {
    private static final String EMAIL_REGEX = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";;

    @Override
    public boolean test(String email) {
        if (email == null) {
            return false;
        }
        return email.matches(EMAIL_REGEX);
    }
}
