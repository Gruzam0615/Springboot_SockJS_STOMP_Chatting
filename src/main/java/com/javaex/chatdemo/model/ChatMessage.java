package com.javaex.chatdemo.model;

import lombok.Data;

@Data
public class ChatMessage {
    
    public enum MessageType {
        ENTER, TALK, FILE, LEAVE
    }
    
    private MessageType messageType;
    private String roomId;   
    private String sender;
    private String message;
    private String sendDate;

}
