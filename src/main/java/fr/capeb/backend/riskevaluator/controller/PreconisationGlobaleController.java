package fr.capeb.backend.riskevaluator.controller;

import fr.capeb.backend.riskevaluator.dto.PreconisationCategorie;
import fr.capeb.backend.riskevaluator.dto.PreconisationGlobale;
import fr.capeb.backend.riskevaluator.exceptions.model.ConflictException;
import fr.capeb.backend.riskevaluator.service.interfaces.PreconisationGlobaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/preconisationglobale")

public class PreconisationGlobaleController {

    @Autowired
    PreconisationGlobaleService preconisationGlobaleService;

    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(preconisationGlobaleService.getAllPreconisationGlobale());
    }


    @GetMapping("/{id}")
    public ResponseEntity getQuestionnaireById(@PathVariable Integer id) {

        var catQue = preconisationGlobaleService.getPreconisationGlobaleById(id);

        if(catQue.isPresent())
            return ResponseEntity.ok(catQue.get());

        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    ResponseEntity deleteCategorieQuestion(@PathVariable Integer id)  {

        var questionnaire = preconisationGlobaleService.getPreconisationGlobaleById(id);
        if(questionnaire.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(preconisationGlobaleService.deletePreconisationGlobale(id));
    }

    @PutMapping("/")
    ResponseEntity replaceCategorieQuestion(@Valid @RequestBody PreconisationGlobale preCat)  {
        var ques = preconisationGlobaleService.getPreconisationGlobaleById(preCat.getIdPreconisationG());
        if(ques.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(preconisationGlobaleService.createOrUpdatePreconisationGlobale(preCat));
    }

    @PostMapping("/")
    ResponseEntity createCategorieQuestion(@Valid  @RequestBody PreconisationGlobale preCat)  {
        var isConflict = preconisationGlobaleService.getPreconisationGlobaleById(preCat.getIdPreconisationG()).isPresent();
        if(isConflict) throw new ConflictException();

        return ResponseEntity.of(preconisationGlobaleService.createOrUpdatePreconisationGlobale(preCat));
    }
}
