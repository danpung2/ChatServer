package com.example.chatprojectspringboot.dto.chat;

import com.example.chatprojectspringboot.entity.Message;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDTO {
    @NotNull
    private int roomId;
    @NotBlank
    private String roomName;
    @NotBlank
    private String nickname;
    @NotBlank
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
