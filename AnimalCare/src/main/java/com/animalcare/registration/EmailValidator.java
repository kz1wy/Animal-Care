package com.animalcare.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
@Service
public class EmailValidator implements Predicate<String> {
    private static final String EMAIL_REGEX = "^[\\w-_.+]*[\\w-_.]@[\\w]+[\\w-.]+[\\w-]$";

    @Override
    public boolean test(String email) {
        if (email == null) {
            return false;
        }
        return email.matches(EMAIL_REGEX);
    }
}
