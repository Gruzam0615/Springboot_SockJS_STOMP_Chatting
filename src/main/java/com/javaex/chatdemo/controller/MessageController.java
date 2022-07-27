package com.javaex.chatdemo.controller;

import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import com.javaex.chatdemo.model.ChatFileMessage;
import com.javaex.chatdemo.model.ChatMessage;
import com.javaex.chatdemo.model.MessageType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MessageController {
    /*
    @MessageMapping()의 경로가 "/chat/message"이지만 ChatConfig의 setApplicationDestinationPrefixes()를 통해 
    prefix를 "/app"으로 해줬기 때문에 실질 경로는 "/app/chat/message"가 됨
    클라이언트에서 "/app/chat/message"의 경로로 메시지를 보내는 요청을 하면,
    메시지 Controller가 받아서 "topic/chat/room/{roomId}"를 구독하고 있는 클라이언트에게 메시지를 전달하게 됨.
    */
    
    private final SimpMessageSendingOperations sendingOperations;

    @MessageMapping("/chat/message")
    public void enter(ChatMessage message) {
        if((message.getMessageType()).equals(MessageType.ENTER)) {
            message.setMessage(message.getSender() + "님이 입장했습니다.");
        }

        // if(ChatMessage.MessageType.LEAVE.equals(message.getMessageType())) {
        if((message.getMessageType()).equals(MessageType.LEAVE)) {
            message.setMessage(message.getSender() + "님이 퇴장했습니다.");
            log.info("## User: {} has left RoomId: {}", message.getSender(), message.getRoomId());
        }

        sendingOperations.convertAndSend("/topic/chat/room/" + message.getRoomId(), message);
    }

    @MessageMapping("/chat/fileMessage")
    public void sendFile(ChatFileMessage message) {
        sendingOperations.convertAndSend("/topic/chat/room/" + message.getRoomId(), message);
    }

}
