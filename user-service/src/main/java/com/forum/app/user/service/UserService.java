package com.forum.app.user.service;

import com.forum.app.user.entity.User;
import com.forum.app.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User updateUser(Long userId, User user) {
        Optional<User> byId = userRepository.findById(userId);
        if (byId.isPresent()) {
            User existingUser = byId.get();
            existingUser.setUserName(user.getUserName());
            existingUser.setPassword(user.getPassword());
            userRepository.save(existingUser);
            return existingUser;
        }
        return null;
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
