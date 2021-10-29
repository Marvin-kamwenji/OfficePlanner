package com.officeplanner.officeplanner;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        //we use this to encode the password

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "muturi";
        String encodedPassword = encoder.encode(rawPassword);

        System.out.println(encodedPassword);
    }
}
