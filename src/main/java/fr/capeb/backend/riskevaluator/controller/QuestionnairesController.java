package fr.capeb.backend.riskevaluator.controller;

import fr.capeb.backend.riskevaluator.dto.CategorieQuestion;
import fr.capeb.backend.riskevaluator.dto.Metier;
import fr.capeb.backend.riskevaluator.dto.Questionnaire;
import fr.capeb.backend.riskevaluator.exceptions.model.ConflictException;
import fr.capeb.backend.riskevaluator.service.interfaces.MetierService;
import fr.capeb.backend.riskevaluator.service.interfaces.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/questionnaires")
public class QuestionnairesController {
    @Autowired
    public QuestionnaireService questionnairesService;

    @Autowired
    private MetierService pMetierManager;

    //{idQuestionnaire}/categoriesQuestion
    @GetMapping("/")
    public ResponseEntity<Object> getAll() {

        return ResponseEntity.ok(questionnairesService.getAllQuestionnaires());
    }

    @GetMapping("/{id}")
    public ResponseEntity getQuestionnaireById(@PathVariable Integer id) {

        Optional<Questionnaire> questionnaire = questionnairesService.getQuestionnaireById(id);

        if(questionnaire.isPresent())
            return ResponseEntity.ok(questionnairesService.getQuestionnaireById(id));

        return ResponseEntity.notFound().build();
    }


    @PostMapping("/")
    ResponseEntity saveQuestionnaire(@Valid @RequestBody Questionnaire questionnaire) {
        var ques = questionnairesService.getQuestionnaireById(questionnaire.getIdQuestionnaire());
        if(ques.isPresent())
            throw new ConflictException();

        return ResponseEntity.ok(questionnairesService.createQuestionnaire(questionnaire));
    }

    @PutMapping("/")
    ResponseEntity replaceQuestionnaire(@Valid  @RequestBody Questionnaire questionnaire)  {
        var ques = questionnairesService.getQuestionnaireById(questionnaire.getIdQuestionnaire());
        if(ques.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(questionnairesService.UpdateQuestionnaire(questionnaire));
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteQuestionnaire(@PathVariable Integer id)  {

        Optional<Questionnaire> questionnaire = questionnairesService.getQuestionnaireById(id);
        if(questionnaire.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(questionnairesService.deleteQuestionnaire(id));
    }
    @GetMapping("/{aQuestionnaireId}/metiers")
    public ResponseEntity getMetierByQuestionnaireId(@PathVariable Integer aQuestionnaireId) {

        Optional<Questionnaire> questionnaire = questionnairesService.getQuestionnaireById(aQuestionnaireId);
        List<Metier> wMetiers= questionnairesService.getMetiersByQuestionnaireId(aQuestionnaireId);
        if(questionnaire.isPresent()&&!wMetiers.isEmpty())
            return ResponseEntity.ok(wMetiers);

        return ResponseEntity.notFound().build();
    }


    @GetMapping("/{aQuestionnaireId}/questions")
    public ResponseEntity getQuestionsByQuestionnaireIdAndMetiers(@PathVariable Integer aQuestionnaireId,@RequestParam(value="metierId") Set<Integer> metierIds) {

        Optional<Questionnaire> questionnaire = questionnairesService.getQuestionnaireById(aQuestionnaireId);
        List<CategorieQuestion> wCategorieQuestions= questionnairesService.getCategorieQuestionsByQuestionnaireIdAndMetiers(aQuestionnaireId,metierIds);
        if(questionnaire.isPresent()&&!wCategorieQuestions.isEmpty())
            return ResponseEntity.ok(wCategorieQuestions);

        return ResponseEntity.notFound().build();
    }
    @GetMapping("/bymetierids")
    public ResponseEntity<Object> getQuestionnairesByMetiers(@RequestParam(value="metierId") Set<Integer> aMetierIds) {

        return ResponseEntity.ok(pMetierManager.getQuestionnaireByListMetierId(aMetierIds));
    }
}
