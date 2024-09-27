package com.huntercodexs.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class ApiError {

    private String message;
    private HttpStatus status;
    private LocalDateTime timeStamp;
}
