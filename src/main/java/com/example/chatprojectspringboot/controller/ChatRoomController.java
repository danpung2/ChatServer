package com.example.chatprojectspringboot.controller;

import com.example.chatprojectspringboot.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatRoomController {
    private final ChatService chatService;
    // 채팅 리스트 화면
//    @GetMapping("/room")
//    public String rooms(Model model) {
//        return "/chat/room";
//    }
    // 모든 채팅방 목록 반환
    @GetMapping("/room/all")
    public ResponseEntity room() {
        return ResponseEntity.ok(chatService.findAllRoom());
    }
    // 채팅방 생성
    @PostMapping("/room")
    public ResponseEntity createRoom(@RequestParam String roomName) {
        return ResponseEntity.ok(chatService.createRoom(roomName));
    }
    // 채팅방 입장 화면
    @GetMapping("/room/enter")
    public ResponseEntity roomDetail(@RequestParam String roomId) {
        return ResponseEntity.ok(roomId);
    }
    // 특정 채팅방 조회
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ResponseEntity roomInfo(@PathVariable Integer roomId) {
        return ResponseEntity.ok(chatService.findById(roomId));
    }
}
