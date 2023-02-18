package com.example.chatprojectspringboot.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnterChatRoomDTO {
    @NotNull
    private int roomId;
//    @NotNull
//    private int userId;
    private String sender;
}
