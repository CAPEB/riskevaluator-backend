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


   /* @PutMapping("/")
    ResponseEntity replaceQuestionnaire(@RequestBody Questionnaire questionnaire)  {
        var ques = questionService.getQuestionnaireById(questionnaire.getIdQuestionnaire());
        if(ques.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(questionService.createOrUpdateQuestionnaire(questionnaire));
    }
*/
    @PostMapping("/")
    ResponseEntity createQuestionnaire( @Valid @RequestBody Question question)  {
        if(questionService.getQuestionById(question.getIdQuestion()).isPresent())
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