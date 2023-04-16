package com.example.chatprojectspringboot.controller;

import com.example.chatprojectspringboot.dto.room.CreateRoomDTO;
import com.example.chatprojectspringboot.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class RoomController {
    private final ChatService chatService;

    // 모든 채팅방 목록 반환
    @GetMapping("/room/all")
    public ResponseEntity room() {
        return ResponseEntity.ok(chatService.findAllRoom());
    }
    // 채팅방 생성
    @PostMapping("/room")
    public ResponseEntity createRoom(@Validated @RequestBody CreateRoomDTO createRoomDTO) {
        return ResponseEntity.ok(chatService.createRoom(createRoomDTO.getRoomName(), LocalDate.parse(createRoomDTO.getExpiredDate(), DateTimeFormatter.ISO_LOCAL_DATE), LocalTime.parse(createRoomDTO.getExpiredTime(), DateTimeFormatter.ISO_LOCAL_TIME)));
    }
    // 채팅방 입장 화면
    @GetMapping("/room/enter")
    public ResponseEntity roomDetail(@RequestParam Integer roomId) {
        return ResponseEntity.ok(chatService.isValidRoom(roomId));
    }
    // 특정 채팅방 조회
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ResponseEntity roomInfo(@PathVariable Integer roomId) {
        return ResponseEntity.ok(chatService.getChatRoomByRoomId(roomId));
    }
}
