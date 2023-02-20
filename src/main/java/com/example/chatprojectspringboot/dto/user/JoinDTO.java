package com.example.chatprojectspringboot.dto.user;

import com.sun.istack.NotNull;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
public class JoinDTO {
    @NotNull
    private String nickname;
    @NotNull
    private String email;
    @NotNull
    private String password;
}
