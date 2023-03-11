package com.example.chatprojectspringboot.repository;

import com.example.chatprojectspringboot.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}
