package com.example.chatprojectspringboot.service;

import com.example.chatprojectspringboot.dto.user.JoinDTO;
import com.example.chatprojectspringboot.entity.User;
import com.example.chatprojectspringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User join(JoinDTO joinDTO){
        User user = User.builder()
                .nickname(joinDTO.getNickname())
                .email(joinDTO.getEmail())
                .password(passwordEncoder.encode(joinDTO.getPassword()))
                .build();

        return userRepository.save(user).setPassword("");
    }

    public User login(int userId, String refreshToken){
        return userRepository.save(getUserById(userId).setRefreshToken(refreshToken)).setPassword("").setRefreshToken("");
    }

    public User getUserById(int userId){
        return userRepository.findById(userId).get();
    }
}
