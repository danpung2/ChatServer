package com.example.chatprojectspringboot.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="message_id")
    private int messageId;

    @Column(name="room_id")
    private int roomId;

    @Column(name="sender")
    private String sender;

    @Column(name="content")
    private String content;

   public Message setContent(String content){
       this.content = content;
       return this;
   }

}
