package fr.capeb.backend.riskevaluator.controller;

import fr.capeb.backend.riskevaluator.dto.Questionnaire;
import fr.capeb.backend.riskevaluator.exceptions.model.ConflictException;
import fr.capeb.backend.riskevaluator.service.interfaces.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/questionnaires")
public class QuestionnairesController {
    @Autowired
    public QuestionnaireService questionnairesService;

    //{idQuestionnaire}/categoriesQuestion
    @GetMapping("/")
    public ResponseEntity<Object> getAll() {

        return ResponseEntity.ok(questionnairesService.getAllQuestionnaires());
    }

    @GetMapping("/{id}")
    public ResponseEntity getQuestionnaireById(@PathVariable Integer id) {

        Optional<Questionnaire> questionnaire = questionnairesService.getQuestionnaireById(id);

        if(questionnaire.isPresent())
            return ResponseEntity.ok(questionnairesService.getAllQuestionnaires());

        return ResponseEntity.notFound().build();
    }


    @PostMapping("/")
    ResponseEntity saveQuestionnaire(@Valid @RequestBody Questionnaire questionnaire) {
        var ques = questionnairesService.getQuestionnaireById(questionnaire.getIdQuestionnaire());
        if(ques.isPresent())
            throw new ConflictException();

        return ResponseEntity.ok(questionnairesService.createOrUpdateQuestionnaire(questionnaire));
    }

    @PutMapping("/")
    ResponseEntity replaceQuestionnaire(@Valid  @RequestBody Questionnaire questionnaire)  {
        var ques = questionnairesService.getQuestionnaireById(questionnaire.getIdQuestionnaire());
        if(ques.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(questionnairesService.createOrUpdateQuestionnaire(questionnaire));
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteQuestionnaire(@PathVariable Integer id)  {

        Optional<Questionnaire> questionnaire = questionnairesService.getQuestionnaireById(id);
        if(questionnaire.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(questionnairesService.deleteQuestionnaire(id));
    }


}
