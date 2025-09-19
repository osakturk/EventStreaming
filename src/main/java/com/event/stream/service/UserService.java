package com.event.stream.service;

import com.event.stream.model.User;
import com.event.stream.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public Page<User> getUsers(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }
}
