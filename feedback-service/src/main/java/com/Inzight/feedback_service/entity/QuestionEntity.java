package com.Inzight.feedback_service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    private String question;

    private String type;

    private boolean required;

    @ElementCollection
    @CollectionTable(
            name = "question_options",
            joinColumns = @JoinColumn(name = "question_id")
    )
    @Column(name = "option_value")
    private List<String> options;
}