package fr.capeb.backend.riskevaluator.controller;

import fr.capeb.backend.riskevaluator.dto.Question;
import fr.capeb.backend.riskevaluator.exceptions.model.ConflictException;
import fr.capeb.backend.riskevaluator.service.interfaces.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/questions")
public class QuestionsController {
    @Autowired
    public QuestionService questionService;



    @PostMapping("/")
    ResponseEntity createQuestion(@Valid @RequestBody Question question)  {
        var ques = questionService.getQuestionById(question.getIdQuestion());
        if(ques.isPresent())
            throw new ConflictException();

        return ResponseEntity.of(questionService.createOrUpdateQuestion(question));
    }

    @PutMapping("/")
    ResponseEntity updateQuestion( @Valid @RequestBody Question question)  {
        var ques = questionService.getQuestionById(question.getIdQuestion());
        if(ques.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.of(questionService.createOrUpdateQuestion(question));
    }
}