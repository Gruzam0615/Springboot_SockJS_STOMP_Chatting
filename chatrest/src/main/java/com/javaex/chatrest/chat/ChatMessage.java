package com.javaex.chatrest.chat;

import lombok.Data;

@Data
public class ChatMessage {
    
    public enum Status {
        ENTER, MESSAGE, LEAVE
    }

    private Status status;
    private String roomId;
    private String sender;
    private String message;
    private String sendDate;

}
