package com.javaex.chatrest.util;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CustomResponse {

    private int responsehttpCode;
    private Object responseItem;
    private LocalDateTime responseTime;
    
}
