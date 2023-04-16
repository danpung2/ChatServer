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
public class EnterRoomMessageDTO {
    @NotNull
    private int roomId;
    @NotBlank
    private String roomName;
    @NotBlank
    private String nickname;

    public Message toEntity(int roomId, String roomName, String nickname) {
        return Message.builder()
                .roomId(roomId)
                .roomName(roomName)
                .sender("System")
                .content(nickname + " 님이 입장하였습니다.")
                .build();
    }
}
