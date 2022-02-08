package fr.capeb.backend.riskevaluator.controller;

import fr.capeb.backend.riskevaluator.dto.Metier;
import fr.capeb.backend.riskevaluator.exceptions.model.ConflictException;
import fr.capeb.backend.riskevaluator.service.interfaces.MetierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/metiers")
public class MetiersController {
    @Autowired
    public MetierService metierService;

    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok(metierService.getAllMetier());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getMetierById(@PathVariable Integer id) {
        var wMetier = metierService.getMetierById(id);

        if(wMetier.isPresent())
            return ResponseEntity.ok(wMetier.get());

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    ResponseEntity createQuestion(@Valid @RequestBody Metier metier)  {
        var met = metierService.getMetierById(metier.getIdMetier());
        if(met.isPresent())
            throw new ConflictException();

        return ResponseEntity.of(metierService.createOrUpdateMetier(metier));
    }

    @PutMapping("/")
    ResponseEntity updateQuestion( @Valid @RequestBody Metier metier)  {
        var met = metierService.getMetierById(metier.getIdMetier());
        if(met.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.of(metierService.createOrUpdateMetier(metier));
    }
}
