package com.Inzight.feedback_service.service;

import com.Inzight.feedback_service.dto.FeedbackDto;
import com.Inzight.feedback_service.entity.FeedbackEntity;
import com.Inzight.feedback_service.exception.FeedBackNotFoundException;
import com.Inzight.feedback_service.exception.UserNotFoundException;
import com.Inzight.feedback_service.mapper.FeedbackMapper;
import com.Inzight.feedback_service.producer.FeedbackProducer;
import com.Inzight.feedback_service.repository.FeedBackRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    final FeedbackMapper mapper;

    final FeedBackRepo repo;

    final FeedbackProducer producer;

    public FeedbackDto addFeedBack(FeedbackDto feedbackDto) {

        FeedbackEntity feedback = mapper.toFeedBackEntity(feedbackDto);
        FeedbackEntity savedFeedback = repo.save(feedback);
        FeedbackDto savedDto = mapper.toFeedbackDto(savedFeedback);
        producer.send(savedDto);
        return savedDto;
    }

    public List<FeedbackDto> getFeedBack(Long surveyId) {
        List<FeedbackEntity> feedbackEntityList = repo.findBySurveyId(surveyId).orElseThrow(
                ()->new UserNotFoundException("User not found"));

        return mapper.toFeedbackDtoList(feedbackEntityList);
    }

    public FeedbackDto getFeedBackById(Long feedbackId) {
        FeedbackEntity feedback = repo.findByFeedbackId(feedbackId).orElseThrow(
                ()-> new FeedBackNotFoundException("Feed Back not found"));

        return mapper.toFeedbackDto(feedback);
    }
}
