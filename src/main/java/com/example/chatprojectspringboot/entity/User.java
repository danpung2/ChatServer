package com.example.chatprojectspringboot.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int userId;

    @Column(name="nickname")
    private String nickname;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="refresh_token")
    private String refreshToken;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="room_Id")
    private Room room;

    @JsonFormat(pattern="yyyy.MM.dd/HH:mm/E")
    @Column(name="created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    public User setNickname(String nickname){
        this.nickname = nickname;
        return this;
    }

    public User setEmail(String email){
        this.email = email;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User setRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
        return this;
    }
}
