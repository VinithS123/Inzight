package com.Inzight.ai_analysis_service.service;

import com.Inzight.ai_analysis_service.entity.AgentResultEntity;
import com.Inzight.ai_analysis_service.repository.AgentResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgentResultService {

    private final AgentResultRepository repository;

    public AgentResultEntity save(
            Long feedbackId,
            String agentType,
            Object result,
            float[] embedding) {

        AgentResultEntity entity = new AgentResultEntity();

        entity.setFeedbackId(feedbackId);
        entity.setAgentType(agentType);
        entity.setResult(result);
        entity.setEmbedding(embedding);
        entity.setFeedbackId(feedbackId);
        return repository.save(entity);
    }
}