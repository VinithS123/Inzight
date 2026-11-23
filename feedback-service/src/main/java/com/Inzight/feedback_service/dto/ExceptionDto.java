package com.Inzight.feedback_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ExceptionDto {

    private LocalDateTime timeStamp;

    private String message;

    private String details;

    private String path;

}
