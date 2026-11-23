package com.Inzight.ai_analysis_service.agent;

import com.Inzight.ai_analysis_service.tool.InsightTools;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InsightAgent {

    private final ChatClient.Builder chatClientBuilder;
    private final InsightTools insightTools;

    public String chat(Long surveyId, String message) {

        ChatClient chatClient = chatClientBuilder.build();

        return chatClient
                .prompt()
                .system("""
                        You are an Insight Agent for a survey platform.

                        Analyze survey feedback and provide accurate,
                        actionable insights.

                        Use the available tools whenever necessary.
                        Do not invent information.

                        Answer the user's question clearly and concisely.
                        """)
                .user("""
                        Survey ID: %d

                        User question:
                        %s
                        """.formatted(surveyId, message))
                .tools(insightTools)
                .call()
                .content();
    }
}