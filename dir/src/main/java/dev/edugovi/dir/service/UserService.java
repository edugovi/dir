package dev.edugovi.dir.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.edugovi.dir.model.User;
import dev.edugovi.dir.model.UserRepository;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByIdNumAndPasswordAndEnabled(Long idNum, String password) {
        return userRepository.findByIdNumAndPasswordAndEnabled(idNum, password);
    }

    public User findByIdStrAndPasswordAndEnabled(String idStr, String password) {
        return userRepository.findByIdStrAndPasswordAndEnabled(idStr, password);
    }

    public User findByIdAndPasswordAndEnabled(Long id, String password) {
        return userRepository.findByIdAndPasswordAndEnabled(id, password);
    }

    
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

}
