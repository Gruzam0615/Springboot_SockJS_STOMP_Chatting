package com.javaex.chatrest.chat;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="ChatRoom")
public class ChatRoom {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatRoomIdx;

    @Id
    private String roomId;

    @Column
    private String roomName;

    @Column
    private Long usersIdx;

    @Column
    private Long companysIdx;

    @Column
    private String roomLogMeta;

    @Column
    private LocalDateTime roomCreatedDate;

    public ChatRoom() {}

    public ChatRoom(String roomName, Long usersIdx) {
        this.roomId = UUID.randomUUID().toString();
        this.roomName = roomName;
        this.usersIdx = usersIdx;
        this.companysIdx = Long.parseLong("1");
        this.roomLogMeta = "데이터 로그파일 주소";
        this.roomCreatedDate = LocalDateTime.now();
    }

    public static ChatRoom create(String roomName, Long usersIdx, Long companysIdx) {
        ChatRoom room = new ChatRoom();
        room.roomId = UUID.randomUUID().toString();
        room.roomName = roomName;
        room.usersIdx = usersIdx;
        room.companysIdx = companysIdx;
        room.roomLogMeta = "";
        room.roomCreatedDate = LocalDateTime.now();

        return room;
    }

}
