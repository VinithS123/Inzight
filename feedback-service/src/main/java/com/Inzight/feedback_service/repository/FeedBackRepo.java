package com.Inzight.feedback_service.repository;

import com.Inzight.feedback_service.entity.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedBackRepo extends JpaRepository<FeedbackEntity, Long> {

    Optional<List<FeedbackEntity>> findBySurveyId(Long userId);

    Optional<FeedbackEntity> findByFeedbackId(Long feedbackId);

}
