package com.Inzight.ai_analysis_service.dto;

import lombok.Data;

@Data
public class InsightChatRequestDto {

    private Long surveyId;

    private String message;
}