package com.Inzight.feedback_service.dto;

import lombok.Data;

import java.util.Map;

@Data
public class FeedbackDto {

    private Long userId;

    private Long feedbackId;

    private Long surveyId;

    private Map<String, String> answers;
}