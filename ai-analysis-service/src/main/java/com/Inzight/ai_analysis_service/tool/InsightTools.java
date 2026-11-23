package com.Inzight.ai_analysis_service.tool;

import com.Inzight.ai_analysis_service.dto.FeedbackDto;
import com.Inzight.ai_analysis_service.dto.SurveyDto;
import com.Inzight.ai_analysis_service.entity.AgentResultEntity;
import com.Inzight.ai_analysis_service.feign.FeedbackServiceClient;
import com.Inzight.ai_analysis_service.repository.AgentResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InsightTools {

    private final AgentResultRepository agentResultRepository;

    private final EmbeddingModel embeddingModel;

    private final FeedbackServiceClient feedbackClient;


    @Tool(description = "Get all AI analysis results for a feedback")
    public List<AgentResultEntity> getFeedbackResults(
            Long feedbackId) {

        return agentResultRepository.findByFeedbackId(feedbackId);
    }


    @Tool(description = "Get a specific AI agent result for a feedback")
    public AgentResultEntity getAgentResult(
            Long feedbackId,
            String agentType) {

        return agentResultRepository
                .findByFeedbackIdAndAgentType(
                        feedbackId,
                        agentType
                )
                .orElse(null);
    }


    @Tool(description = "Search AI analysis results using semantic similarity")
    public List<AgentResultEntity> searchSimilarResults(
            String query) {

        float[] vector = embeddingModel.embed(query);

        String embedding = Arrays.toString(vector);

        return agentResultRepository.searchSimilar(
                embedding,
                5
        );
    }


    @Tool(description = "Get the original feedback using its feedback ID")
    public FeedbackDto getOriginalFeedback(
            Long feedbackId) {

        return feedbackClient.getFeedBackById(feedbackId).getBody();

    }

    @Tool(description = "Get the all feedback using its survey ID")
    public List<FeedbackDto> getFeedbacksBySurveyId(
            Long surveyId) {

        return feedbackClient.getFeedBacks(surveyId).getBody();

    }

    @Tool(description = "Get the survey questions using the survey ID")
    public SurveyDto getSurvey(
            Long surveyId) {

        return feedbackClient.getSurveyById(surveyId).getBody();
    }
}