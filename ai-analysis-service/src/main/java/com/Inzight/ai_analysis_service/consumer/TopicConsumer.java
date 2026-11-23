package com.Inzight.ai_analysis_service.consumer;

import com.Inzight.ai_analysis_service.agent.TopicAgent;
import com.Inzight.ai_analysis_service.dto.FeedbackDto;
import com.Inzight.ai_analysis_service.dto.TopicResultDto;
import com.Inzight.ai_analysis_service.service.AgentResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TopicConsumer {

    private final TopicAgent topicAgent;
    private final EmbeddingModel embeddingModel;
    private final AgentResultService service;

    @KafkaListener(
            topics = "feedback-submitted",
            groupId = "topic-agent"
    )
    public void consume(FeedbackDto feedbackDto) {

        System.out.println(" = = =  = = =  = = =  = = =  = = =  = = =  = = =  = = =  = = =  = = =  = = =  = = =  = = = ");

        long startTime = System.nanoTime();

        log.info("Topic Agent received feedback: {}",
                feedbackDto.getFeedbackId());

        TopicResultDto result =
                topicAgent.analyze(feedbackDto.toString());

        float[] embedding =
                embeddingModel.embed(result.toString());

        service.save(
                feedbackDto.getFeedbackId(),
                "TOPIC",
                result,
                embedding
        );

        long endTime = System.nanoTime();

        double processingTimeMs =
                (endTime - startTime) / 1_000_000.0;

        log.info(
                "Topic Agent completed feedbackId={} in {} ms",
                feedbackDto.getFeedbackId(),
                processingTimeMs
        );

        System.out.println(" = = =  = = =  = = =  = = =  = = =  = = =  = = =  = = =  = = =  = = =  = = =  = = =  = = = ");

    }
}