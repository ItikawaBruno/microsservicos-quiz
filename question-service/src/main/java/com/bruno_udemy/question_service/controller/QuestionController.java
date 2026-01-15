package com.bruno_udemy.question_service.controller;


import com.bruno_udemy.question_service.domain.Question;
import com.bruno_udemy.question_service.domain.QuestionWrapper;
import com.bruno_udemy.question_service.domain.Response;
import com.bruno_udemy.question_service.dto.QuestionDTO;
import com.bruno_udemy.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService service;
    @Autowired
    private QuestionService questionService;

    @Autowired
    private Environment environment;

    @GetMapping("/allQuestion")
    public ResponseEntity<List<QuestionDTO>> getAllQuestion(){
        try{
            return service.getAllQuestions();
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/createQuestion")
    public ResponseEntity<String> createQuestion(@RequestBody QuestionDTO dto){
        return service.createQuestion(dto);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
        return service.getQuestionByCategory(category);
    }

    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String categoryName, @RequestParam Integer numQuestions){
        return questionService.getQuestionForQuiz(categoryName, numQuestions);
    }

    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Long> questionsId){
        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionFromId(questionsId);
    }

    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }

    // generate
    // getQuestions (questionId)
    // getScore

}
