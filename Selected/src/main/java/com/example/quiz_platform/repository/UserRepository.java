package com.example.quiz_platform.repository;

import com.example.quiz_platform.model.User;
import com.example.quiz_platform.model.User.Role;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.io.File;
import java.io.IOException;

@Repository
public class UserRepository {

    // change to ArrayList and get rid of base constructor?
    private List<User> userList;
    private static final String USERS_JSON = "src/main/resources/users.json";
    ObjectMapper mapper = new ObjectMapper();

    public UserRepository() {
        this.userList = loadUserJSON();
    }

    public List<User> loadUserJSON() {
        File file = new File(USERS_JSON);

        try {
            return mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, User.class));
        } catch (IOException e) {
            System.err.println("Error:" + e.getMessage());
            return new ArrayList<>();
        }
    }

    // need to fix?
    public void saveUserList() {
        try {
            mapper.writeValue(new File(USERS_JSON), userList);
        } catch (IOException e) {
            System.err.println("Cannot save user list: " + e.getMessage());
        }
    }

    public User createUser(String username, String password, Role role) {

        if(findUserByUsername(username) != null) {
            throw new IllegalArgumentException("Username Already Exists");
        }

        String id = UUID.randomUUID().toString();
        User newUser = new User(id, username, password, role);

        // need to implement UserRepository as a place to save users
        userList.add(newUser);
        saveUserList();

        return newUser;
    }

    // add params to update user information?
    // handle the way its currently implemented?
    public boolean updateUser(User user) {
        User dummyUser = null;

        for (User _user : userList) {
            if (_user.getId().equals(user.getId())) {
                dummyUser = _user;
                break;
            }
        }

        if (dummyUser != null) {
            // this can be changed depending on how we want to update user information
            dummyUser.setUsername(user.getUsername());
            dummyUser.setPassword(user.getPassword());
            dummyUser.setRole(user.getRole());
        } else {
            System.out.println("User ID Does Not Exist!");
            return false;
        }

        saveUserList();
        return true;
    }

    public List<User> listUsers() {
        return new ArrayList<>(userList);
    }

    public User findUserById(String id) {
        List<User> users = loadUserJSON();
        for (User _user : users) {
            if (_user.getId().equals(id)) {
                return _user;
            }
        }

        throw new RuntimeException("User with UUID" + id + " does not exist");
    }

    public User findUserByUsername(String username) {
        List<User> users = loadUserJSON();
        for (User _user : users) {
            if (_user.getUsername().equals(username)) {
                return _user;
            }
        }

        return null;
    }

    public void deleteUser(String id) {
        List<User> users = loadUserJSON();

        try {

            for (User _user : users) {
                if (_user.getId().equals(id)) {
                    userList.remove(_user);
                }
            }

            saveUserList();

        } catch (Exception e) {
            System.err.println("Cannot Delete User: " + e.getMessage());
        }
    }

}
