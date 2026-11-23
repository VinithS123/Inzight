package com.Inzight.ai_analysis_service.agent;
import jakarta.annotation.PostConstruct;
import com.Inzight.ai_analysis_service.dto.PriorityResultDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PriorityAgent {

    private final ChatClient chatClient;


    public PriorityResultDto analyze(String feedback) {

        log.info("PriorityAgent STARTED");

        try {

            PriorityResultDto result = chatClient
                    .prompt()
                    .system("""
                            You are a priority analysis agent.

                            Analyze the feedback and determine how important
                            or urgent the issue is.

                            Return:
                            - priority: LOW, MEDIUM, or HIGH
                            - confidence: number between 0 and 1
                            - reason: short explanation for the priority
                            - evidence: important sentences from the feedback
                              that support the priority

                            Return only the requested structured result.
                            """)
                    .user(feedback)
                    .call()
                    .entity(PriorityResultDto.class);

            log.info("PriorityAgent PASSED");

            return result;

        } catch (Exception e) {

            log.error("PriorityAgent FAILED", e);

            throw e;
        }
    }
}