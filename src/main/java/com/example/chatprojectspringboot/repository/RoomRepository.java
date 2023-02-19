package com.example.chatprojectspringboot.repository;

import com.example.chatprojectspringboot.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    Optional<Room> findRoomByRoomId(int roomId);
    Optional<Room> findRoomByRoomIdAndUser(int roomId, int userId);
    List<Room> findAllByOrderByRoomIdDesc();
}
