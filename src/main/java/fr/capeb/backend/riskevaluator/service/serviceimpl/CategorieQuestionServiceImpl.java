package fr.capeb.backend.riskevaluator.service.serviceimpl;

import fr.capeb.backend.riskevaluator.dto.CategorieQuestion;
import fr.capeb.backend.riskevaluator.exceptions.ExceptionMsg;
import fr.capeb.backend.riskevaluator.exceptions.model.ConflictException;
import fr.capeb.backend.riskevaluator.exceptions.model.CreateOrUpdateException;
import fr.capeb.backend.riskevaluator.exceptions.model.CustomException;
import fr.capeb.backend.riskevaluator.exceptions.model.MappingDataException;
import fr.capeb.backend.riskevaluator.model.CategorieQuestionEntity;
import fr.capeb.backend.riskevaluator.repository.QuestionCategorieRepository;
import fr.capeb.backend.riskevaluator.repository.QuestionnaireRepository;
import fr.capeb.backend.riskevaluator.service.interfaces.CategorieQuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Transactional

public class CategorieQuestionServiceImpl implements CategorieQuestionService {

    @Autowired
    private QuestionCategorieRepository questionCategorieRepo;

    @Autowired
    private QuestionnaireRepository questionnaireRepo;

    @Autowired
    private ModelMapper modelMapper;

    public Set<CategorieQuestion> getAllCategorieQuestion() {
        return questionCategorieRepo.findAll()
                .stream()
                .map(stop -> modelMapper.map(stop, CategorieQuestion.class))
                .collect(Collectors.toSet());
    }



    @Override
    public Optional<CategorieQuestion> categorieQuestionById(Integer quesId) {

        if(quesId == null) return Optional.empty();

        var questionnaire = questionCategorieRepo.findById(quesId);
        if (questionnaire.isPresent()) {
            return Optional.of(modelMapper.map(questionnaire.get(),CategorieQuestion.class));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Object> deleteCategorieQuestion(Integer id) {
        questionCategorieRepo.deleteById(id);
        return Optional.empty();
    }

    @Override
    public Optional<CategorieQuestion> updateCategorieQuestion(CategorieQuestion obj) {

        questionnaireRepo.findById(obj.getIdQuestionnaire()).orElseThrow(()-> new CustomException("questionnaire"+ ExceptionMsg.ID_NOT_FOUND.value));
        CategorieQuestionEntity catQues = Optional.of(modelMapper.map(obj, CategorieQuestionEntity.class)).orElseThrow(MappingDataException::new);


        var res=  questionCategorieRepo.findByQuestionaire(catQues.idQuestionnaire,catQues.idCategorie);
        if(res.isPresent()) throw new ConflictException();

        var updated = Optional.of(questionCategorieRepo.save(catQues)).orElseThrow(CreateOrUpdateException::new);
        return Optional.of(modelMapper.map(updated,CategorieQuestion.class));

    }

}
