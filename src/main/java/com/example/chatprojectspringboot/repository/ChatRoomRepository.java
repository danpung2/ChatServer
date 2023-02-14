package com.example.chatprojectspringboot.repository;

import com.example.chatprojectspringboot.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer> {
    Optional<ChatRoom> findChatRoomByRoomId(int roomId);
    List<ChatRoom> findAll();
}
