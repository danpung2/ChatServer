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
public class EnterRoomMessageDTO {
    @NotNull
    private int roomId;

    public Message toEntity(int roomId){
        return Message.builder()
                .roomId(roomId)
                .sender("GUEST")
                .content(" 님이 입장하였습니다.")
                .build();

    }
}
