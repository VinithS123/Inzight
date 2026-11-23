package com.Inzight.feedback_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class SurveyDto {

    private Long userId;

    private String title;

    private String description;

    private List<QuestionDto> questions;
}