package com.bruno_udemy.question_service.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String category;

    private String dificultyLevel;

    private String option1;

    private String option2;

    private String option3;

    private String option4;

    private String questionTitle;

    private String rightAnswer;

}
