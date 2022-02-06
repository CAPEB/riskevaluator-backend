package fr.capeb.backend.riskevaluator.service.serviceimpl;

import fr.capeb.backend.riskevaluator.dto.Question;
import fr.capeb.backend.riskevaluator.exceptions.model.CreateOrUpdateException;
import fr.capeb.backend.riskevaluator.exceptions.model.MappingDataException;
import fr.capeb.backend.riskevaluator.model.QuestionEntity;
import fr.capeb.backend.riskevaluator.repository.QuestionCategorieRepository;
import fr.capeb.backend.riskevaluator.repository.QuestionRepository;
import fr.capeb.backend.riskevaluator.service.interfaces.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Transactional
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepo ;
    @Autowired
    private QuestionCategorieRepository questionCategorieRepo;

    @Autowired
    private ModelMapper modelMapper;



    @Override
    public Set<Question> getAllQuestion() {
        return questionRepo.findAll()
                .stream()
                .map(stop -> modelMapper.map(stop, Question.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<Question> getQuestionById(Integer quesId) {

        if(quesId == null) return Optional.empty();


        Optional<QuestionEntity> questionnaire = questionRepo.findById(quesId);
        if (questionnaire.isPresent()) {
            return Optional.of(modelMapper.map(questionnaire.get(),Question.class));
        }
        return Optional.empty();
    }


    @Override
    public Optional<Question> createOrUpdateQuestion(Question question) {

        var ques = Optional.of(modelMapper.map(question, QuestionEntity.class)).orElseThrow(MappingDataException::new);
        var updated = Optional.of(questionRepo.save(ques)).orElseThrow(CreateOrUpdateException::new);
        return Optional.of(modelMapper.map(updated,Question.class));
    }

    @Override
    public Optional<Object> deleteQuestion(Integer quesId) {
        questionRepo.deleteById(quesId);
        return Optional.empty();
    }
}
