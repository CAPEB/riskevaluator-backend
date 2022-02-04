package fr.capeb.backend.riskevaluator.controller;

import fr.capeb.backend.riskevaluator.dto.CategorieQuestion;
import fr.capeb.backend.riskevaluator.dto.PreconisationCategorie;
import fr.capeb.backend.riskevaluator.exceptions.model.ConflictException;
import fr.capeb.backend.riskevaluator.service.interfaces.CategorieQuestionService;
import fr.capeb.backend.riskevaluator.service.interfaces.PreconisationCategorieService;
import fr.capeb.backend.riskevaluator.service.interfaces.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/preconisationcategorie")

public class PreconisationCategorieController {

    @Autowired
    PreconisationCategorieService preconisationCategorieService;

    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(preconisationCategorieService.getAllPreconisationCategorie());
    }


    @GetMapping("/{id}")
    public ResponseEntity getQuestionnaireById(@PathVariable Integer id) {

        var catQue = preconisationCategorieService.getPreconisationCategorieById(id);

        if(catQue.isPresent())
            return ResponseEntity.ok(catQue.get());

        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    ResponseEntity deleteCategorieQuestion(@PathVariable Integer id)  {

        var questionnaire = preconisationCategorieService.getPreconisationCategorieById(id);
        if(questionnaire.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(preconisationCategorieService.deletePreconisationCategorie(id));
    }

    @PutMapping("/")
    ResponseEntity replaceCategorieQuestion(@Valid @RequestBody PreconisationCategorie preCat)  {
        var ques = preconisationCategorieService.getPreconisationCategorieById(preCat.getIdPreconisation());
        if(ques.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(preconisationCategorieService.createOrUpdatePreconisationCategorie(preCat));
    }

    @PostMapping("/")
    ResponseEntity createCategorieQuestion(@Valid  @RequestBody PreconisationCategorie preCat)  {
        var isConflict = preconisationCategorieService.getPreconisationCategorieById(preCat.getIdPreconisation()).isPresent();
        if(isConflict) throw new ConflictException();

        return ResponseEntity.of(preconisationCategorieService.createOrUpdatePreconisationCategorie(preCat));
    }
}
