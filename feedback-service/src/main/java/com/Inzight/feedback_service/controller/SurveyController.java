package com.Inzight.feedback_service.controller;

import com.Inzight.feedback_service.dto.SurveyDto;
import com.Inzight.feedback_service.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class SurveyController {


    final SurveyService service;

    @PostMapping("/surveys")
    public ResponseEntity<SurveyDto> addSurvey(@RequestBody SurveyDto surveyDto){
        System.out.println(surveyDto);
        return ResponseEntity.ok(service.addSurvey(surveyDto));

    }

    @GetMapping("/surveys/{surveyId}")
    public ResponseEntity<SurveyDto> getSurveyById(@PathVariable Long surveyId){

        return ResponseEntity.ok(service.getSurveyById(surveyId));

    }

    @GetMapping("/surveys/user/{userId}")
    public ResponseEntity<List<SurveyDto>> getSurveys(@PathVariable Long userId){

        return ResponseEntity.ok(service.getSurveys(userId));

    }
}
