package fr.capeb.backend.riskevaluator.service.serviceimpl;

import fr.capeb.backend.riskevaluator.dto.CategorieQuestion;
import fr.capeb.backend.riskevaluator.dto.ExceptionMsg;
import fr.capeb.backend.riskevaluator.dto.Question;
import fr.capeb.backend.riskevaluator.dto.Questionnaire;
import fr.capeb.backend.riskevaluator.exceptions.model.ConflictException;
import fr.capeb.backend.riskevaluator.exceptions.model.CreateOrUpdateException;
import fr.capeb.backend.riskevaluator.exceptions.model.CustomException;
import fr.capeb.backend.riskevaluator.exceptions.model.MappingDataException;
import fr.capeb.backend.riskevaluator.model.CategorieQuestionEntity;
import fr.capeb.backend.riskevaluator.model.QuestionEntity;
import fr.capeb.backend.riskevaluator.model.QuestionnaireEntity;
import fr.capeb.backend.riskevaluator.repository.QuestionCategorieRepository;
import fr.capeb.backend.riskevaluator.repository.QuestionRepository;
import fr.capeb.backend.riskevaluator.repository.QuestionnaireRepository;
import fr.capeb.backend.riskevaluator.service.interfaces.QuestionService;
import fr.capeb.backend.riskevaluator.service.interfaces.QuestionnaireService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
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
        Optional<QuestionEntity> questionnaire = questionRepo.findById(quesId);
        if (questionnaire.isPresent()) {
            return Optional.of(modelMapper.map(questionnaire.get(),Question.class));
        }
        return Optional.empty();
    }


    @Override
    public Optional<Question> createOrUpdateQuestion(Question question) {

        questionCategorieRepo.findById(question.getIdCategorie()).orElseThrow(()-> new CustomException("The categorie" + ExceptionMsg.ID_NOT_FOUND.value));

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
