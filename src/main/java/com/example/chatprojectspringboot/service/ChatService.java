package com.example.chatprojectspringboot.service;

import com.example.chatprojectspringboot.dto.chat.EnterRoomMessageDTO;
import com.example.chatprojectspringboot.entity.Message;
import com.example.chatprojectspringboot.repository.RoomRepository;
import com.example.chatprojectspringboot.entity.Room;
import com.example.chatprojectspringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    //채팅방 불러오기
    public List<Room> findAllRoom() {
        return roomRepository.findAllByOrderByRoomIdDesc();
    }

    //채팅방 하나 불러오기
    public Room getChatRoomByRoomId(int roomId) {
        return roomRepository.findRoomByRoomId(roomId).get();
    }

    //채팅방 생성
    public Room createRoom(String name) {
        Room room = Room.builder()
                .roomName(name)
                .build();

        return roomRepository.save(room);
    }

    public Message enterRoom(EnterRoomMessageDTO enterRoomMessageDTO) {
        return enterRoomMessageDTO.toEntity(enterRoomMessageDTO.getRoomId(), enterRoomMessageDTO.getRoomName());
    }

    public boolean isValidRoom(int roomId){
        return roomRepository.findRoomByRoomId(roomId).isPresent();
    }


}
