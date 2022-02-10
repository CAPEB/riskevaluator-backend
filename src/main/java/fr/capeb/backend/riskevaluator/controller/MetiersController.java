package fr.capeb.backend.riskevaluator.controller;

import fr.capeb.backend.riskevaluator.dto.Metier;
import fr.capeb.backend.riskevaluator.exceptions.model.ConflictException;
import fr.capeb.backend.riskevaluator.service.interfaces.MetierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/metiers")
public class MetiersController {
    @Autowired
    public MetierService pMetierManager;

    @GetMapping("/")
    public ResponseEntity<Object> getAll() {

        return ResponseEntity.ok(pMetierManager.getAllMetier());
    }

    @GetMapping("/{aMetierId}")
    public ResponseEntity<Object> getMetierById(@PathVariable Integer aMetierId) {
        var wMetier = pMetierManager.getMetierById(aMetierId);

        if(wMetier.isPresent())
            return ResponseEntity.ok(wMetier.get());

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    ResponseEntity saveQuestion(@Valid @RequestBody Metier metier)  {
        var met = pMetierManager.getMetierById(metier.getIdMetier());
        if(met.isPresent())
            throw new ConflictException();

        return ResponseEntity.of(pMetierManager.createOrUpdateMetier(metier));
    }

    @PutMapping("/")
    ResponseEntity updateQuestion( @Valid @RequestBody Metier metier)  {
        var met = pMetierManager.getMetierById(metier.getIdMetier());
        if(met.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.of(pMetierManager.createOrUpdateMetier(metier));
    }

    @DeleteMapping("/{aMetierId}")
    public ResponseEntity<Object> deleteMetierById(@PathVariable Integer aMetierId) {
        var wMetier = pMetierManager.getMetierById(aMetierId);

        if(wMetier.isPresent())
            return ResponseEntity.ok(pMetierManager.deleteMetierById(aMetierId));

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/questionnaires")
    public ResponseEntity<Object> getQuestionnairesByMetiers(@RequestParam(value="metierId") List<Integer> aMetierIds) {

        return ResponseEntity.ok(pMetierManager.getQuestionnaireByListMetierId(aMetierIds));
    }
}
