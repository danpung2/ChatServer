package com.example.chatprojectspringboot.entity;

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
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="room_id")
    private int roomId;
    @Column(name="room_name")
    private String roomName;
    @JsonFormat(pattern="yyyy.MM.dd/HH:mm/E")
    @Column(name="created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
}
