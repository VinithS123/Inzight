package com.Inzight.feedback_service.controller;

import com.Inzight.feedback_service.dto.FeedbackDto;
import com.Inzight.feedback_service.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class FeedbackController {

    final FeedbackService service;

    @PostMapping("/feedbacks")
    public ResponseEntity<FeedbackDto> addFeedBack(@RequestBody FeedbackDto feedbackDto){
        return ResponseEntity.ok(service.addFeedBack(feedbackDto));
    }

    @GetMapping("/feedbacks/user/{surveyId}")
    public ResponseEntity<List<FeedbackDto>> getFeedBacks(@PathVariable Long surveyId ){
        return ResponseEntity.ok(service.getFeedBack(surveyId));
    }

    @GetMapping("/feedbacks/{feedbackId}")
    public ResponseEntity<FeedbackDto> getFeedBackById(@PathVariable Long feedbackId ){
        return ResponseEntity.ok(service.getFeedBackById(feedbackId));
    }

}
