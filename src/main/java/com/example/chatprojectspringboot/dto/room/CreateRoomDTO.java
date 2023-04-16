package com.example.chatprojectspringboot.dto.room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoomDTO {
    @NotBlank
    private String roomName;
    @NotBlank
    private String expiredDate;
    @NotBlank
    private String expiredTime;
}
