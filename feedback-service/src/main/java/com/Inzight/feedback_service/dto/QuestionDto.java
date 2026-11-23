package com.Inzight.feedback_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionDto {

    private String question;

    private String type;

    private boolean required;

    private List<String> options;
}