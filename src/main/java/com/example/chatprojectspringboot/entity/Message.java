package com.example.chatprojectspringboot.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    public enum MessageType {
        ENTER, TALK
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="message_id")
    private int messageId;

    @Column(name="message_type")
    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    @Column(name="room_id")
    private int roomId;

    @Column(name="sender")
    private String sender;

    @Column(name="content")
    private String content;

}
