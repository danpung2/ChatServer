package com.example.chatprojectspringboot.controller;

import com.example.chatprojectspringboot.dto.chat.ChatMessageDTO;
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
        Message message = chatService.enterMessage(enterRoomMessageDTO);
        sendingOperations.convertAndSend("/topic/chat/room/" + message.getRoomId(), message);
    }

    @MessageMapping("/chat/message")
    public void send(ChatMessageDTO chatMessageDTO) {
        Message message = chatService.sendMessage(chatMessageDTO);
        sendingOperations.convertAndSend("/topic/chat/room/" + message.getRoomId(), message);
    }
}
