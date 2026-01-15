package com.bruno_udemy.quiz_service.repository;


import com.bruno_udemy.quiz_service.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
