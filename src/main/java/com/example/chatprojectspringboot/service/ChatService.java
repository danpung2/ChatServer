package com.example.chatprojectspringboot.service;

import com.example.chatprojectspringboot.repository.ChatRoomRepository;
import com.example.chatprojectspringboot.entity.ChatRoom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRoomRepository chatRoomRepository;
    //채팅방 불러오기
    public List<ChatRoom> findAllRoom() {
        return chatRoomRepository.findAll();
    }

    //채팅방 하나 불러오기
    public ChatRoom findById(int roomId) {
        return chatRoomRepository.findChatRoomByRoomId(roomId).get();
    }

    //채팅방 생성
    public ChatRoom createRoom(String name) {
        ChatRoom chatRoom = ChatRoom.builder()
                .roomName(name)
                .build();

        return chatRoomRepository.save(chatRoom);
    }
}
