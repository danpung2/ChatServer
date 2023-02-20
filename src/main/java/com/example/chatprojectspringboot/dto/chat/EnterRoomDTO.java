package com.example.chatprojectspringboot.dto.chat;

import com.example.chatprojectspringboot.entity.User;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnterRoomDTO {
    @NotNull
    private int roomId;
    private User user;
}
