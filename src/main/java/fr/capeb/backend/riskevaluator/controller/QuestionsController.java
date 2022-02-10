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
    public QuestionService pQuestionManager;
    @Autowired
    public ReponseService pResponseManager;


    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(pQuestionManager.getAllQuestion());
    }
    @GetMapping("/{aQuestionId}")
    public ResponseEntity<Object> getQuestionById(@PathVariable Integer aQuestionId) {
        var wQuestion = pQuestionManager.getQuestionById(aQuestionId);

        if(wQuestion.isPresent())
            return ResponseEntity.ok(wQuestion.get());

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    ResponseEntity createQuestion(@Valid @RequestBody Question question)  {
        var ques = pQuestionManager.getQuestionById(question.getIdQuestion());
        if(ques.isPresent())
            throw new ConflictException();

        return ResponseEntity.of(pQuestionManager.createOrUpdateQuestion(question));
    }

    @PutMapping("/")
    ResponseEntity updateQuestion( @Valid @RequestBody Question question)  {
        var ques = pQuestionManager.getQuestionById(question.getIdQuestion());
        if(ques.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.of(pQuestionManager.createOrUpdateQuestion(question));
    }

    @PostMapping("/{aIdQuestion}/reponses")
    ResponseEntity addResponse(@Valid @PathVariable Integer aIdQuestion, @Valid @RequestBody Reponse aReponse){
        var ques = pQuestionManager.getQuestionById(aIdQuestion);
        if(ques.isEmpty())
            return ResponseEntity.notFound().build();
        if(aReponse.getIdReponse()!=null && pResponseManager.getReponseById(aReponse.getIdReponse()).isPresent()){
            throw new ConflictException();
        }
        return ResponseEntity.of(pResponseManager.createOrUpdateReponse(aReponse));
    }

    @GetMapping("/{aQuestionId}")
    public ResponseEntity<Object> deleteQuestionById(@PathVariable Integer aQuestionId) {
        var wQuestion = pQuestionManager.getQuestionById(aQuestionId);

        if(wQuestion.isPresent())
            return ResponseEntity.ok(pQuestionManager.deleteQuestionById(aQuestionId));

        return ResponseEntity.notFound().build();
    }

}
