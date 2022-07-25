package com.javaex.chatrest.chat;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.chatrest.util.CustomResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/chat")
public class ChatRoomController {
    
    @Autowired private ChatService chatService;

    /**
     * "현재 서버에 존재하는 모든 채팅방 조회"하는 메서드 입니다.
     * @return
     */
    @GetMapping("/room")
    public CustomResponse roomList() {
        CustomResponse result = new CustomResponse();
        
        List<ChatRoom> chatRooms = chatService.findAll();

        result.setResponsehttpCode(HttpStatus.OK.value());
        result.setResponseItem(chatRooms);
        result.setResponseTime(LocalDateTime.now());

        return result;
    }

    /**
     * "채팅방을 추가"하는 메서드
     * @param roomName
     * @param usersIdx
     * @return
     */
    @PostMapping("/room")
    public CustomResponse roomCreate(
        @RequestParam("roomName") String roomName,
        @RequestParam("usersIdx") Long usersIdx
    ) {
        log.info("# {} has created", roomName);
        CustomResponse result = new CustomResponse();
        ChatRoom some = chatService.roomCreate(roomName, usersIdx);

        result.setResponsehttpCode(HttpStatus.ACCEPTED.value());
        result.setResponseItem(some);
        result.setResponseTime(LocalDateTime.now());

        return result;
    }

    @GetMapping("/room/enter/{roomId}")
    public CustomResponse roomDetail(
        @PathVariable(value="roomId") String roomId,
        @RequestParam(value="usersName") String usersName
    ) {
        CustomResponse result = new CustomResponse();
        return result;
    }

}
