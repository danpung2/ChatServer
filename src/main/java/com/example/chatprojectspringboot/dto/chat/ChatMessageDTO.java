package com.example.chatprojectspringboot.dto.chat;

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
    private String nickname;
    @NotNull
    private String content;
}
