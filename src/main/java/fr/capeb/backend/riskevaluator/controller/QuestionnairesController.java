package fr.capeb.backend.riskevaluator.controller;

import fr.capeb.backend.riskevaluator.model.StatusEntity;
import fr.capeb.backend.riskevaluator.repository.QuestionnaireRepository;
import fr.capeb.backend.riskevaluator.repository.StatusRepository;
import fr.capeb.backend.riskevaluator.service.interfaces.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}