package com.javaex.chatdemo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.chatdemo.model.ChatRoom;
import com.javaex.chatdemo.service.ChatService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/chat")
public class ChatRoomController {

    @Autowired private ChatService chatService;

    // 채팅 리스트 화면
    @GetMapping("/room")
    public String room(Model model) {
        return "/chat/room";
    }
    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> rooms() {
        return chatService.findAllRoom();
    }
    // 채팅방 생성
    @PostMapping("/room")
    @ResponseBody
    public ChatRoom createRoom(
        @RequestParam("name") String roomName,
        @RequestParam("usersName") String usersName
    ) {
        return chatService.createRoom(roomName, Long.parseLong("1"));
    }
    // @PostMapping("/room")
    // @ResponseBody
    // public int createRoom(
    //     @RequestParam("roomName") String roomName
    // ) {
    //     ChatRoom cr = new ChatRoom(roomName, Long.parseLong("1"));
    //     return chatService.CreateChatRoom(cr);
    // }

    // 채팅방 입장 화면
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, 
        @PathVariable String roomId,
        @RequestParam(value="usersName") String usersName,
        @RequestParam(value="messageType") String messageType
    ) {
        model.addAttribute("roomId", roomId);
        log.info("## User: {} get in RoomId: {}\n## T: {}", usersName, roomId, LocalDateTime.now());
        return "/chat/roomdetail";
    }
    // 특정 채팅방 조회
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId, Model model) {
        
        ChatRoom transaction = chatService.findById(roomId);
        model.addAttribute("room", transaction);

        return transaction;
    }
}
