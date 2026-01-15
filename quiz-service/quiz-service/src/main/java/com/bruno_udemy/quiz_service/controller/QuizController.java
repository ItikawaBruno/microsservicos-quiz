package com.bruno_udemy.quiz_service.controller;


import com.bruno_udemy.quiz_service.domain.QuestionWrapper;
import com.bruno_udemy.quiz_service.domain.Response;
import com.bruno_udemy.quiz_service.dto.QuizDTO;
import com.bruno_udemy.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO dto){
        return quizService.createQuiz(dto.getCategoryName(), dto.getNumQuestions(), dto.getTitle());
    }

    @PostMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Long id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Long id, @RequestBody List<Response> responses){
        return quizService.submitQuiz(id, responses);
    }

}
