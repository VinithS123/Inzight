package com.Inzight.ai_analysis_service.agent;

import com.Inzight.ai_analysis_service.dto.SentimentResultDto;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

@Component
public class SentimentAgent {

    private final ChatClient chatClient;

    public SentimentAgent(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public SentimentResultDto analyze(String feedback) {

        return chatClient
                .prompt()
                .system("""
                        You are a sentiment analysis agent.

                        Analyze the feedback and return:
                        - sentiment: POSITIVE, NEGATIVE, or NEUTRAL
                        - confidence: number between 0 and 1
                        - reason: short explanation
                        - evidence: important sentences from the feedback

                        Return only the requested structured result.
                        """)
                .user(feedback)
                .call()
                .entity(SentimentResultDto.class);
    }
}