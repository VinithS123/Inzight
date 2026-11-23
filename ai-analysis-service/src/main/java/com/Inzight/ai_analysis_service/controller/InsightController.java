package com.Inzight.ai_analysis_service.controller;

import com.Inzight.ai_analysis_service.agent.InsightAgent;
import com.Inzight.ai_analysis_service.dto.InsightChatRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/insights")
@RequiredArgsConstructor
public class InsightController {

    private final InsightAgent insightAgent;

    @PostMapping("/chat")
    public String chat(
            @RequestBody InsightChatRequestDto request) {

        return insightAgent.chat(
                request.getSurveyId(),
                request.getMessage()
        );
    }
}