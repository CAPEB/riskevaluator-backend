package fr.capeb.backend.riskevaluator.controller;

import fr.capeb.backend.riskevaluator.dto.Questionnaire;
import fr.capeb.backend.riskevaluator.exceptions.model.CreateOrUpdateException;
import fr.capeb.backend.riskevaluator.model.StatusEntity;
import fr.capeb.backend.riskevaluator.repository.QuestionnaireRepository;
import fr.capeb.backend.riskevaluator.repository.StatusRepository;
import fr.capeb.backend.riskevaluator.service.interfaces.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/Questionnaires")
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


    @PutMapping("/")
    ResponseEntity replaceEphad(@RequestBody Questionnaire questionnaire) {
        
        return ResponseEntity.ok(questionnairesService.createOrUpdateQuestionnaire(questionnaire));
    }
}