package fr.capeb.backend.riskevaluator.util;

import fr.capeb.backend.riskevaluator.model.Status;
import fr.capeb.backend.riskevaluator.repository.StatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommandLineAppStartupRunner implements CommandLineRunner {

    @Autowired
    public StatusRepository statusRepo;

    @Value("${test.env.variable.port}")
    private String port;
    @Value("${test.env.variable.dbname}")
    private String dbName;
    @Value("${test.env.variable.user}")
    private String user;
    @Value("${test.env.variable.pass}")
    private String pass;
    @Value("${test.env.variable.host}")
    private String host;


    @Value("${spring.datasource.url}")
    private String devUrl;
    @Value("${spring.datasource.username}")
    private String devUser;
    @Value("${spring.datasource.password}")
    private String devPass;

    @Override
    public void run(String...args) throws Exception {
        log.info("START TEST ENV VARIBALES ==================================== \n");
        log.info("---"+host);
        log.info("---"+port);
        log.info("---"+dbName);
        log.info("---"+user);
        log.info("---"+pass);
        log.info(" END TEST ENV VARIBALES ==================================== \n");



        log.info("START DEV ENV VARIBALES ==================================== \n");
        log.info("---"+devUrl);
        log.info("---"+devUser);
        log.info("---"+devPass);
        log.info(" END DEV ENV VARIBALES ==================================== \n");

        Status status = Status.builder().id(1).ok("CAPEB ENV status OK").build();
        statusRepo.save(status);
    }
}
