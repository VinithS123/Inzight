package com.Inzight.ai_analysis_service.repository;

import com.Inzight.ai_analysis_service.entity.AgentResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AgentResultRepository
        extends JpaRepository<AgentResultEntity, Long> {

    // All AI results for one feedback
    List<AgentResultEntity> findByFeedbackId(Long feedbackId);

    // One specific agent result for one feedback
    Optional<AgentResultEntity> findByFeedbackIdAndAgentType(
            Long feedbackId,
            String agentType
    );

    // Semantic similarity search using pgvector
    @Query(value = """
            SELECT *
            FROM agent_results
            ORDER BY embedding <=> CAST(:embedding AS vector)
            LIMIT :limit
            """, nativeQuery = true)
    List<AgentResultEntity> searchSimilar(
            @Param("embedding") String embedding,
            @Param("limit") int limit
    );
}