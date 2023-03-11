package com.example.chatprojectspringboot.dto.chat;

import com.example.chatprojectspringboot.entity.Message;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDTO {
    @NotNull
    private int roomId;
    @NotNull
    private String roomName;
    @NotNull
    private String nickname;
    @NotNull
    private String content;

    public Message toEntity(int roomId, String roomName, String nickname, String content){
        return Message.builder()
                .roomId(roomId)
                .roomName(roomName)
                .sender(nickname)
                .content(content)
                .build();

    }
}
