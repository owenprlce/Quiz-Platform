package com.example.quiz_platform.service;

import org.springframework.stereotype.Service;

import com.example.quiz_platform.model.User;
import com.example.quiz_platform.model.User.Role;
import com.example.quiz_platform.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String username, String password, Role role) {
        if (userRepository.findUserByUsername(username) != null) {
            throw new IllegalArgumentException("Username Exists");
        }

        return userRepository.createUser(username, password, role);
    }

    public boolean updateUser(String id, String _username, String _password, Role _role) {
        User toUpdate = getUserById(id);

        if (toUpdate != null) {
            toUpdate.setUsername(_username);
            toUpdate.setPassword(_password);
            toUpdate.setRole(_role);

            return userRepository.updateUser(toUpdate);
        }

        return false;
    }

    public User getUserById(String id) {
        return userRepository.findUserById(id);
    }

    public User getUserByUsername(String id) {
        return userRepository.findUserByUsername(id);
    }

    public List<User> listUsers() {
        return userRepository.listUsers();
    }

    public void deleteUser(String id) {
        userRepository.deleteUser(id);
    }
}
