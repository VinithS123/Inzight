package com.Inzight.ai_analysis_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class SentimentResultDto {

    private String sentiment;

    private double confidence;

    private String reason;

    private List<String> evidence;
}
