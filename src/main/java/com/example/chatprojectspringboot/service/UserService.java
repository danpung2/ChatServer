package com.example.chatprojectspringboot.service;

import com.example.chatprojectspringboot.entity.User;
import com.example.chatprojectspringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUserById(int userId){
        return userRepository.findById(userId).get();
    }
}
