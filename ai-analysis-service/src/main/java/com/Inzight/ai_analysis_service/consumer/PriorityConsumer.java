package com.Inzight.ai_analysis_service.consumer;

import com.Inzight.ai_analysis_service.agent.PriorityAgent;
import com.Inzight.ai_analysis_service.dto.FeedbackDto;
import com.Inzight.ai_analysis_service.dto.PriorityResultDto;
import com.Inzight.ai_analysis_service.service.AgentResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PriorityConsumer {

    private final AgentResultService service;
    private final PriorityAgent priorityAgent;
    private final EmbeddingModel embeddingModel;

    @KafkaListener(
            topics = "feedback-submitted",
            groupId = "priority-agent"
    )
    public void consume(FeedbackDto feedbackDto) {

        System.out.println(" = = =  = = =  = = =  = = =  = = =  = = =  = = =  = = =  = = =  = = =  = = = ");

        long startTime = System.nanoTime();

        log.info("Priority Agent received feedback: {}",
                feedbackDto.getFeedbackId());

        PriorityResultDto result =
                priorityAgent.analyze(feedbackDto.toString());

        float[] embedding =
                embeddingModel.embed(result.toString());

        service.save(
                feedbackDto.getFeedbackId(),
                "PRIORITY",
                result,
                embedding
        );

        long endTime = System.nanoTime();

        double processingTimeMs =
                (endTime - startTime) / 1_000_000.0;

        log.info(
                "Priority Agent completed feedbackId={} in {} ms",
                feedbackDto.getFeedbackId(),
                processingTimeMs
        );

        System.out.println(" = = =  = = =  = = =  = = =  = = =  = = =  = = =  = = =  = = =  = = =  = = = ");

    }
}