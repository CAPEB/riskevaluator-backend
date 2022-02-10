package fr.capeb.backend.riskevaluator.controller;

import fr.capeb.backend.riskevaluator.dto.Evaluation;
import fr.capeb.backend.riskevaluator.exceptions.model.ConflictException;
import fr.capeb.backend.riskevaluator.service.interfaces.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/evaluations")
public class EvaluationController {
    @Autowired
    private EvaluationService pEvaluationService;

    @GetMapping("/")
    public ResponseEntity<Object> getAll(){
        return ResponseEntity.ok(pEvaluationService.getAllEvaluation());
    }

    @GetMapping("/{aEvaluationId}")
    public ResponseEntity<Object> getEvaluationById(@PathVariable Integer aEvaluationId){
        var wEvaluation =pEvaluationService.getEvaluationById(aEvaluationId);
        if(wEvaluation.isPresent()){
            return ResponseEntity.ok(wEvaluation.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<Evaluation> saveEvaluation(@Valid @RequestBody Evaluation aEvaluation){
        var wEvaluation =pEvaluationService.getEvaluationById(aEvaluation.getIdEvaluation());
        if(wEvaluation.isPresent()){
            throw new ConflictException();
        }
        return ResponseEntity.of(pEvaluationService.SaveEvaluation(aEvaluation));
    }

    @DeleteMapping("/{aEvaluationId}")
    public ResponseEntity<Object> deleteEvaluationById(@PathVariable Integer aEvaluationId){
        var wEvaluation =pEvaluationService.getEvaluationById(aEvaluationId);
        if(wEvaluation.isPresent()){
            return ResponseEntity.ok(pEvaluationService.deleteEvaluation(aEvaluationId));
        }
        return ResponseEntity.notFound().build();
    }

}
