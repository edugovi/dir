package dev.edugovi.dir.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public final class Utilities {

    public static String getPasswordEncoded(String rawPassword) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncoded = passwordEncoder.encode(rawPassword);
        return passwordEncoded;
    }
}
