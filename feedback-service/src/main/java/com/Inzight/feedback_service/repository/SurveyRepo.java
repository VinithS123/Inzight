package com.Inzight.feedback_service.repository;

import com.Inzight.feedback_service.entity.SurveyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SurveyRepo extends JpaRepository<SurveyEntity,Long> {

     Optional<SurveyEntity> findBySurveyId(Long surveyId);

     Optional<List<SurveyEntity>> findByUserId(Long userId);

}
