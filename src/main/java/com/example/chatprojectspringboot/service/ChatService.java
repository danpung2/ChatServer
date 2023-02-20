package com.example.chatprojectspringboot.service;

import com.example.chatprojectspringboot.dto.chat.EnterRoomDTO;
import com.example.chatprojectspringboot.entity.User;
import com.example.chatprojectspringboot.repository.RoomRepository;
import com.example.chatprojectspringboot.entity.Room;
import com.example.chatprojectspringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public EnterRoomDTO enterRoom(int roomId, int userId){
        // TODO: findUserByRoomId -> 이 채팅방에 대한 유저 정보 유무 확인
//        if(roomRepository.findRoomByRoomIdAndUser(roomId, userId).isPresent()){
//
//        } else {
//
//        }

        User dummy = User.builder()
                .userId(99)
                .nickname("tester")
                .email("tester@gmail.com")
                .password("qwerty12345")
                .createdAt(LocalDateTime.now())
                .build();

        return EnterRoomDTO.builder()
        .roomId(roomId)
        .user(dummy)
        .build();
    }
}
