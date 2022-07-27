package com.javaex.chatdemo.model;

import lombok.Data;

@Data
public class ChatFileMessage {
    
    private MessageType messageType;
    private String roomId;
    private String sender;
    private Object fileMessage;
    private String sendDate;

}
