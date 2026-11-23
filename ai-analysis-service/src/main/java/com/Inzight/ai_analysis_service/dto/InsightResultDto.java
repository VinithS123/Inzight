package com.Inzight.ai_analysis_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class InsightResultDto {
    private String summary;
    private List<String> insights;
    private List<String> recommendations;
}
