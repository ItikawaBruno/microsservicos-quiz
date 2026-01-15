package com.bruno_udemy.question_service.service;


import com.bruno_udemy.question_service.domain.Question;
import com.bruno_udemy.question_service.domain.QuestionWrapper;
import com.bruno_udemy.question_service.domain.Response;
import com.bruno_udemy.question_service.dto.QuestionDTO;
import com.bruno_udemy.question_service.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository repository;
    @Autowired
    private QuestionRepository questionRepository;

    public ResponseEntity<List<QuestionDTO>> getAllQuestions(){

        List<Question> questions = repository.findAll();

        List<QuestionDTO> questionDTOs = questions.stream().map( c -> new QuestionDTO(c.getCategory(),c.getDificultyLevel(), c.getOption1(), c.getOption2(),c.getOption3(), c.getOption4(), c.getQuestionTitle(), c.getRightAnswer())).toList();

        return new ResponseEntity<>(questionDTOs, HttpStatus.OK) ;
    }

    public ResponseEntity<String> createQuestion(QuestionDTO dto) {

        Question question = new Question();

        question.setCategory(dto.category());
        question.setOption1(dto.option1());
        question.setOption2(dto.option2());
        question.setOption3(dto.option3());
        question.setOption4(dto.option4());
        question.setQuestionTitle(dto.questionTitle());
        question.setRightAnswer(dto.rightAnswer());

        repository.save(question);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        try{
            return new ResponseEntity<>(repository.getQuestionsByCategory(category), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Integer>> getQuestionForQuiz(String categoryName, Integer numQuestions) {
        List<Integer> questions =repository.getRandomQuestionsByCategory(categoryName, numQuestions);

        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(List<Long> questionsId) {
        List<QuestionWrapper> wrappers = new ArrayList<>();

        List<Question> questions = new ArrayList<>();

        for(Long id : questionsId){
            questions.add(questionRepository.findById(id).get());
        }

        for (Question question : questions){
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setTitle(question.getQuestionTitle());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());
            wrappers.add(wrapper);
        }

        return new ResponseEntity<>(wrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {

        int right = 0;

        for (Response response : responses){
            Question question = questionRepository.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRightAnswer())){
                right++;
            }
        }

        return new ResponseEntity<>(right, HttpStatus.OK);

    }
}
