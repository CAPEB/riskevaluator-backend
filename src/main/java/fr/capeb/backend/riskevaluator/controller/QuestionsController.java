package fr.capeb.backend.riskevaluator.controller;

import fr.capeb.backend.riskevaluator.dto.Question;
import fr.capeb.backend.riskevaluator.dto.Reponse;
import fr.capeb.backend.riskevaluator.exceptions.model.ConflictException;
import fr.capeb.backend.riskevaluator.service.interfaces.QuestionService;
import fr.capeb.backend.riskevaluator.service.interfaces.ReponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/questions")
public class QuestionsController {
    @Autowired
    public QuestionService questionService;
    @Autowired
    public ReponseService pResponseManager;


    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(questionService.getAllQuestion());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getQuestionById(@PathVariable Integer id) {
        var wQuestion = questionService.getQuestionById(id);

        if(wQuestion.isPresent())
            return ResponseEntity.ok(wQuestion.get());

        return ResponseEntity.notFound().build();
    }

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

    @PostMapping("/{aIdQuestion}/reponses")
    ResponseEntity addResponse(@Valid @PathVariable Integer aIdQuestion, @Valid @RequestBody Reponse aReponse){
        var ques = questionService.getQuestionById(aIdQuestion);
        if(ques.isEmpty())
            return ResponseEntity.notFound().build();
        if(aReponse.getIdReponse()!=null && pResponseManager.getReponseById(aReponse.getIdReponse()).isPresent()){
            throw new ConflictException();
        }
        return ResponseEntity.of(pResponseManager.createOrUpdateReponse(aReponse));
    }

}
