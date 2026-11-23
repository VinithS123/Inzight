package com.Inzight.ai_analysis_service.agent;

import com.Inzight.ai_analysis_service.dto.TopicResultDto;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

@Component
public class TopicAgent {

    private final ChatClient chatClient;

    public TopicAgent(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public TopicResultDto analyze(String feedback) {

        return chatClient
                .prompt()
                .system("""
                        You are a topic analysis agent.

                        Analyze the feedback and identify the main topics
                        or issues discussed.

                        Return:
                        - topics: list of the main topics
                        - reason: short explanation of why these topics
                          were identified
                        - evidence: important sentences from the feedback
                          that support the topics

                        Return only the requested structured result.
                        """)
                .user(feedback)
                .call()
                .entity(TopicResultDto.class);
    }
}