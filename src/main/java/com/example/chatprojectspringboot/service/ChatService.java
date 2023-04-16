package com.example.chatprojectspringboot.service;

import com.example.chatprojectspringboot.dto.chat.ChatMessageDTO;
import com.example.chatprojectspringboot.dto.chat.EnterRoomMessageDTO;
import com.example.chatprojectspringboot.entity.Message;
import com.example.chatprojectspringboot.repository.MessageRepository;
import com.example.chatprojectspringboot.repository.RoomRepository;
import com.example.chatprojectspringboot.entity.Room;
import com.example.chatprojectspringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {
    private final RoomRepository roomRepository;
    private final MessageRepository messageRepository;
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
    public Room createRoom(String name, LocalDate expiredDate, LocalTime expiredTime) {
        Room room = Room.builder()
                .roomName(name)
                .expiredDate(expiredDate)
                .expiredTime(expiredTime)
                .build();

        return roomRepository.save(room);
    }

    public Message enterMessage(EnterRoomMessageDTO enterRoomMessageDTO) {
        return messageRepository.save(enterRoomMessageDTO.toEntity(enterRoomMessageDTO.getRoomId(), enterRoomMessageDTO.getRoomName(), enterRoomMessageDTO.getNickname()));
    }

    public Message sendMessage(ChatMessageDTO chatMessageDTO){
        return messageRepository.save(chatMessageDTO.toEntity(chatMessageDTO.getRoomId(), chatMessageDTO.getRoomName(), chatMessageDTO.getNickname(), chatMessageDTO.getContent()));
    }

    public boolean isValidRoom(int roomId){
        if(roomRepository.findRoomByRoomId(roomId).isEmpty()){
            return false;
        }
        Room room = roomRepository.findRoomByRoomId(roomId).orElse(null);

        if(room != null && room.getExpiredDate().isAfter(LocalDate.now()) && room.getExpiredTime().isAfter(LocalTime.now())){
            return true;
        }

        return false;
    }

}
