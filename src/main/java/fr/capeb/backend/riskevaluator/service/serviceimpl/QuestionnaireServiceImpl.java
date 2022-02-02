package fr.capeb.backend.riskevaluator.service.serviceimpl;

import fr.capeb.backend.riskevaluator.dto.Questionnaire;
import fr.capeb.backend.riskevaluator.repository.QuestionnaireRepository;
import fr.capeb.backend.riskevaluator.service.interfaces.QuestionnaireService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Component
public class QuestionnaireServiceImpl implements QuestionnaireService {

    @Autowired
    private QuestionnaireRepository questionnairesRepo ;

    @Autowired
    private ModelMapper modelMapper;

    public Set<Questionnaire> getAllQuestionnaires() {
        return questionnairesRepo.findAll()
                .stream()
                .map(stop -> modelMapper.map(stop, Questionnaire.class))
                .collect(Collectors.toSet());
    }
}
