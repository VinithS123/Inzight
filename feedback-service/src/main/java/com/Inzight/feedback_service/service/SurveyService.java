package com.Inzight.feedback_service.service;

import com.Inzight.feedback_service.dto.SurveyDto;
import com.Inzight.feedback_service.entity.SurveyEntity;
import com.Inzight.feedback_service.exception.SurveyNotFoundException;
import com.Inzight.feedback_service.exception.UserNotFoundException;
import com.Inzight.feedback_service.mapper.SurveyMapper;
import com.Inzight.feedback_service.repository.SurveyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SurveyService {

    final SurveyRepo repo;

    final SurveyMapper mapper;

    public SurveyDto addSurvey(SurveyDto surveyDto) {

        SurveyEntity survey = mapper.toSurveyEntity(surveyDto);
        System.out.println(survey);
        SurveyDto dto = mapper.toSurveyDto(repo.save(survey));
        System.out.println(dto);
        return dto;
    }

    public SurveyDto getSurveyById(Long surveyId) {
        SurveyEntity survey = repo.findBySurveyId(surveyId).orElseThrow(
                ()->  new SurveyNotFoundException("Survey not found"));

        return mapper.toSurveyDto(survey);
    }

    public  List<SurveyDto> getSurveys(Long userId) {
        List<SurveyEntity> surveyList = repo.findByUserId(userId).orElseThrow(
                ()->  new UserNotFoundException("User not found"));

        return mapper.toListSurveyDto(surveyList);
    }
}
