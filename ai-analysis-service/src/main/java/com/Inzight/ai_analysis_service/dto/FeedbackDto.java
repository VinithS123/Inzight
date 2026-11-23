package com.Inzight.ai_analysis_service.dto;

import lombok.Data;

import java.util.Map;

@Data
public class FeedbackDto {

    private String userId;

    private Long feedbackId;

    private Long surveyId;

    private Map<String, String> answers;
}