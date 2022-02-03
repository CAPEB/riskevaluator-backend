package fr.capeb.backend.riskevaluator.controller;

import fr.capeb.backend.riskevaluator.dto.Reponse;
import fr.capeb.backend.riskevaluator.service.interfaces.ReponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/Reponses")
public class ReponsesController {
    @Autowired
    public ReponseService pResponseManager;

    @GetMapping()
    public ResponseEntity<Object> getAll() {

        return ResponseEntity.ok(pResponseManager.getAllReponses());
    }
    @GetMapping("/{aReponseid}")
    public ResponseEntity getReponseById(@PathVariable Integer aReponseid) {

        Optional<Reponse> wReponse = pResponseManager.getReponseById(aReponseid);

        if(wReponse.isPresent())
            return ResponseEntity.ok(wReponse);

        return ResponseEntity.notFound().build();
    }


    @PutMapping("/")
    Object replaceReponse(@RequestBody Reponse aReponse) {
        if(aReponse.getIdReponse()==null)
            return ResponseEntity.notFound();
        return ResponseEntity.ok(pResponseManager.createOrUpdateReponse(aReponse));
    }

}
