package com.example.chatprojectspringboot.controller;

import com.example.chatprojectspringboot.dto.chat.EnterRoomMessageDTO;
import com.example.chatprojectspringboot.entity.Message;
import com.example.chatprojectspringboot.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {
    private final SimpMessageSendingOperations sendingOperations;
    private final ChatService chatService;

    @MessageMapping("/chat/message/enter")
    public void enter(EnterRoomMessageDTO enterRoomMessageDTO) {
        Message message = chatService.enterRoom(enterRoomMessageDTO);
//        sendingOperations.convertAndSend("/topic/chat/room/" + message.getRoomId(), message);
        sendingOperations.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}
