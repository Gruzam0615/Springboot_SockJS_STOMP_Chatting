package com.javaex.chatrest.chat;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.chatrest.util.CustomResponse;

@RestController
@RequestMapping("/chat")
public class ChatRoomController {
    
    @Autowired private ChatService chatService;

    @GetMapping("/room")
    public CustomResponse roomList() {
        CustomResponse result = new CustomResponse();
        
        List<ChatRoom> chatRooms = chatService.findAll();

        result.setResponsehttpCode(HttpStatus.OK.value());
        result.setResponseItem(chatRooms);
        result.setResponseTime(LocalDateTime.now());

        return result;
    }

    @PostMapping("/room")
    public CustomResponse roomCreate(
        @RequestParam("roomName") String roomName,
        @RequestParam("usersIdx") Long usersIdx
    ) {
        CustomResponse result = new CustomResponse();
        ChatRoom some = chatService.roomCreate(roomName, usersIdx);

        result.setResponsehttpCode(HttpStatus.ACCEPTED.value());
        result.setResponseItem(some);
        result.setResponseTime(LocalDateTime.now());

        return result;
    }

}
