package com.Inzight.ai_analysis_service.entity;

import com.Inzight.ai_analysis_service.dto.SentimentResultDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Data
@Table(name = "agent_results")
public class AgentResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long feedbackId;

    private String agentType;

    private Long surveyId;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Object result;

    @JdbcTypeCode(SqlTypes.VECTOR)
    @Column(columnDefinition = "vector(768)")
    private float[] embedding;
}