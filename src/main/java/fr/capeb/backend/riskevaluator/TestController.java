package fr.capeb.backend.riskevaluator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TestController {

    @GetMapping("api/status")
    public String getStatus() {
        return "CAPEB ENV status OK New";
    }
}
