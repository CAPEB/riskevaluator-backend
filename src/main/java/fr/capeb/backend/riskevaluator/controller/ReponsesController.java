package fr.capeb.backend.riskevaluator.controller;

import fr.capeb.backend.riskevaluator.dto.Questionnaire;
import fr.capeb.backend.riskevaluator.dto.Reponse;
import fr.capeb.backend.riskevaluator.service.interfaces.ReponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/reponses")
public class ReponsesController {
    @Autowired
    public ReponseService pResponseManager;

    @GetMapping()
    public ResponseEntity<Object> getAll() {

        return ResponseEntity.ok(pResponseManager.getAllReponses());
    }
    @GetMapping("/{aReponseId}")
    public ResponseEntity getReponseById(@PathVariable Integer aReponseId) {

        Optional<Reponse> wReponse = pResponseManager.getReponseById(aReponseId);

        if(wReponse.isPresent())
            return ResponseEntity.ok(wReponse);

        return ResponseEntity.notFound().build();
    }


    @PutMapping("/")
    ResponseEntity updateReponse(@RequestBody Reponse aReponse) {
        if(aReponse.getIdReponse()==null) {
            return ResponseEntity.badRequest().build();
        }
        int wReponseId = aReponse.getIdReponse();

        Optional<Reponse> wReponse = pResponseManager.getReponseById(wReponseId);
        if(wReponse.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pResponseManager.createOrUpdateReponse(aReponse));
    }
    @DeleteMapping("/{aReponseId}")
    ResponseEntity deleteReponse(@PathVariable Integer aReponseId)  {
        Optional<Reponse> wReponse = pResponseManager.getReponseById(aReponseId);
        if(wReponse.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(pResponseManager.deleteReponse(aReponseId));
    }


}
