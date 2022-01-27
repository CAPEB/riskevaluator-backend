package fr.capeb.backend.riskevaluator.util;

import fr.capeb.backend.riskevaluator.model.Status;
import fr.capeb.backend.riskevaluator.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    @Autowired
    public StatusRepository statusRepo;

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String user;
    @Value("${spring.datasource.password}")
    private String pass;


    @Override
    public void run(String...args) throws Exception {
        System.out.println("MOHAMMED");
        System.out.println("---"+url);
        System.out.println("---"+user);
        System.out.println("---"+pass);

        Status status = Status.builder().id(1).ok("CAPEB ENV status OK").build();
        statusRepo.save(status);
    }
}
