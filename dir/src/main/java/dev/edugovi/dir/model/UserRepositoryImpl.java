package dev.edugovi.dir.model;

import java.util.Optional;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserRepositoryImpl implements CustomizedUserRepository {

    private UserRepository userRepository;


    public UserRepositoryImpl(@Lazy UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByIdNumAndPasswordAndEnabled(Long idNum, String rawPassword) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = userRepository.findByIdNum(idNum);

        if (passwordEncoder.matches(rawPassword, user.getPassword()) && user.getEnabled())
            return user;
        return null;
    }

    @Override
    public User findByIdStrAndPasswordAndEnabled(String idStr, String rawPassword) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = userRepository.findByIdStr(idStr);

        if (passwordEncoder.matches(rawPassword, user.getPassword()) && user.getEnabled())
            return user;
        return null;
    }

    @Override
    public User findByIdAndPasswordAndEnabled(Long id, String rawPassword) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Optional<User> user = userRepository.findById(id);

        if (passwordEncoder.matches(rawPassword, user.get().getPassword()) && user.get().getEnabled())
            return user.get();
        return null;
    }
}
