package com.bruno_udemy.question_service.dto;

public record QuestionDTO(String category, String dificultyLevel, String option1, String option2, String option3, String option4, String questionTitle, String rightAnswer) {
}
