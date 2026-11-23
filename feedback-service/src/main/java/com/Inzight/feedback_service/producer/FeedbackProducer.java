package com.Inzight.feedback_service.producer;

import com.Inzight.feedback_service.dto.FeedbackDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class FeedbackProducer {

    private final KafkaTemplate<String, FeedbackDto> kafkaTemplate;

    public void send(FeedbackDto feedbackDto){

        log.info("Sending feedback to Kafka");
        kafkaTemplate.send("feedback-submitted",feedbackDto);
    }

}
