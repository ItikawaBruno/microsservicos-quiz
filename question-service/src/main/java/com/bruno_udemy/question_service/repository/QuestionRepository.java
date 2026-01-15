package com.bruno_udemy.question_service.repository;


import com.bruno_udemy.question_service.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> getQuestionsByCategory(String category);

    @Query(
            value = """
    SELECT *
    FROM question q
    WHERE q.category = ?
    ORDER BY RANDOM()
    LIMIT ?
  """,
            nativeQuery = true
    )
    List<Integer> getRandomQuestionsByCategory(String category, int numQuestions);


}
