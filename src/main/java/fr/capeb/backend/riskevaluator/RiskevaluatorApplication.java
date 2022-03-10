package fr.capeb.backend.riskevaluator;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class RiskevaluatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(RiskevaluatorApplication.class, args);
	}

}

