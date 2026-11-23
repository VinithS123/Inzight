package com.Inzight.ai_analysis_service.feign;

import com.Inzight.ai_analysis_service.dto.FeedbackDto;
import com.Inzight.ai_analysis_service.dto.SurveyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.CacheRequest;
import java.util.List;

@FeignClient(
        name = "feedback-service",
        url = "${feedback-service.url}",
        path = "/api/v1"
)
public interface FeedbackServiceClient {

    @GetMapping("/feedbacks/{feedbackId}")
    ResponseEntity<FeedbackDto> getFeedBackById(@PathVariable Long feedbackId);

    @GetMapping("/surveys/{surveyId}")
    ResponseEntity<SurveyDto> getSurveyById(@PathVariable Long surveyId);

    @GetMapping("/feedbacks/user/{surveyId}")
    ResponseEntity<List<FeedbackDto>> getFeedBacks(@PathVariable Long surveyId );
}