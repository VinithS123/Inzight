package com.Inzight.ai_analysis_service.dto;


import lombok.Data;

import java.util.List;

@Data
public class TopicResultDto {
    private List<String> topics;
    private String reason;
    private List<String> evidence;
}
