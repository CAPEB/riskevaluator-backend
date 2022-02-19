package fr.capeb.backend.riskevaluator.service.serviceimpl;

import fr.capeb.backend.riskevaluator.dto.Question;
import fr.capeb.backend.riskevaluator.exceptions.model.CreateOrUpdateException;
import fr.capeb.backend.riskevaluator.exceptions.model.MappingDataException;
import fr.capeb.backend.riskevaluator.model.MetierQuestionEntityPK;
import fr.capeb.backend.riskevaluator.model.QuestionEntity;
import fr.capeb.backend.riskevaluator.repository.*;
import fr.capeb.backend.riskevaluator.service.interfaces.QuestionService;
import fr.capeb.backend.riskevaluator.service.interfaces.ReponseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    private MetierRepository pMetierRepo;

    @Autowired
    private MetierQuestionRepository pMetierQuestionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ReponseService pReponseManager;



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
    public Optional<Question> createOrUpdateQuestion(Question aQuestion) {
        aQuestion.getReponses().stream().forEach(wReponse ->wReponse.setQuestion(aQuestion));
        var wQuestion=questionRepo.findById(aQuestion.getIdQuestion());
        var wQuestionIdList=aQuestion.getReponses().stream().map(wReponse->wReponse.getIdReponse()).collect(Collectors.toList());
        if(wQuestion.isPresent()){
            var wResponseToRemove=wQuestion.get().getReponses();
            var ques = Optional.of(modelMapper.map(aQuestion, QuestionEntity.class)).orElseThrow(MappingDataException::new);
            wResponseToRemove.removeAll(ques.getReponses());

            wResponseToRemove.forEach(wReponse->{
                pReponseManager.deleteReponse(wReponse.getIdReponse());

            });
        }
        var wMetierIds=aQuestion.getMetiers().stream().map(wMetier->wMetier.getIdMetier()).collect(Collectors.toList());

        var ques = Optional.of(modelMapper.map(aQuestion, QuestionEntity.class)).orElseThrow(MappingDataException::new);
        ques.setMetiers(ques.getMetiers().stream().map(wMetier ->{
                    var wQuestionMetier = pMetierQuestionRepository.findById(new MetierQuestionEntityPK(aQuestion.getIdQuestion(),wMetier.getMetier().getIdMetier()));
                    if(wQuestionMetier.isPresent() ){
                        return wQuestionMetier.get();
                    }

                    wMetier.setMetier(pMetierRepo.getById(wMetier.getMetier().getIdMetier()));

                    wMetier.setQuestion(ques);

                    return wMetier;
                }).collect(Collectors.toSet()));
        if(wQuestion.isPresent()){
            wQuestion.get().getMetiers().stream().forEach(wMetierQuestion->{
                if(!wMetierIds.contains(wMetierQuestion.getMetier().getIdMetier())){
                    pMetierQuestionRepository.deleteById(wMetierQuestion.getQuestion().getIdQuestion(),wMetierQuestion.getMetier().getIdMetier());
                }
            });
        }
        var updated = Optional.of(questionRepo.save(ques)).orElseThrow(CreateOrUpdateException::new);
        return Optional.of(modelMapper.map(updated,Question.class));
    }

    @Override
    public Optional<Object> deleteQuestionById(Integer quesId) {
        var wQuestionEntity=questionRepo.getById(quesId);
        var wMetierQuestionEntityList=wQuestionEntity.getMetiers();
        wMetierQuestionEntityList.forEach(wMetierQuestionEntity->wMetierQuestionEntity.getMetier().getQuestions().removeAll(wMetierQuestionEntityList));
        wQuestionEntity.getMetiers().removeAll(wMetierQuestionEntityList);
        wQuestionEntity.getReponses().forEach(wReponseEntity->pReponseManager.deleteReponse(wReponseEntity.getIdReponse()));
        questionRepo.delete(wQuestionEntity);


        return Optional.empty();
    }
}
