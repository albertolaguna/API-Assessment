package com.solera.bootcamp.betos.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomResponse {

    private String timestamp;
    private int status;
    private String message;

    public CustomResponse(int status, String message) {
        this.timestamp = java.time.LocalDateTime.now().toString();
        this.status = status;
        this.message = message;
    }

}
