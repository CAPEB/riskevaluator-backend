package fr.capeb.backend.riskevaluator.controller;

import fr.capeb.backend.riskevaluator.dto.CategorieQuestion;
import fr.capeb.backend.riskevaluator.dto.Questionnaire;
import fr.capeb.backend.riskevaluator.exceptions.model.ConflictException;
import fr.capeb.backend.riskevaluator.model.StatusEntity;
import fr.capeb.backend.riskevaluator.repository.StatusRepository;
import fr.capeb.backend.riskevaluator.service.interfaces.CategorieQuestionService;
import fr.capeb.backend.riskevaluator.service.interfaces.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/categoriesQuestion")
public class CategorieQuestionController {

    @Autowired
    CategorieQuestionService categorieQuestionService;

    @Autowired
    QuestionnaireService questionnairesService ;

    @GetMapping("/")
    public ResponseEntity<Object> getAll() {

        return ResponseEntity.ok(categorieQuestionService.getAllCategorieQuestion());
    }

    @GetMapping("/{id}")
    public ResponseEntity getQuestionnaireById(@PathVariable Integer id) {

        var catQue = categorieQuestionService.categorieQuestionById(id);

        if(catQue.isPresent())
            return ResponseEntity.ok(catQue.get());

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteCategorieQuestion(@PathVariable Integer id)  {

        var questionnaire = categorieQuestionService.categorieQuestionById(id);
        if(questionnaire.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(categorieQuestionService.deleteCategorieQuestion(id));
    }

    @PutMapping("/")
    ResponseEntity replaceCategorieQuestion(@Valid @RequestBody CategorieQuestion catQues)  {
        var ques = categorieQuestionService.categorieQuestionById(catQues.getIdCategorie());
        if(ques.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(categorieQuestionService.updateCategorieQuestion(catQues));
    }

    @PostMapping("/")
    ResponseEntity createCategorieQuestion(@Valid  @RequestBody CategorieQuestion catQues)  {
        var isConflict = categorieQuestionService.categorieQuestionById(catQues.getIdCategorie()).isPresent();
        if(isConflict) throw new ConflictException();

        return ResponseEntity.of(categorieQuestionService.updateCategorieQuestion(catQues));
    }


}
