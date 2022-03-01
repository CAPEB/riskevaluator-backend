package fr.capeb.backend.riskevaluator.service.serviceimpl;

import fr.capeb.backend.riskevaluator.dto.CategorieQuestion;
import fr.capeb.backend.riskevaluator.dto.Metier;
import fr.capeb.backend.riskevaluator.dto.Questionnaire;
import fr.capeb.backend.riskevaluator.exceptions.model.CreateOrUpdateException;
import fr.capeb.backend.riskevaluator.exceptions.model.MappingDataException;
import fr.capeb.backend.riskevaluator.model.QuestionnaireEntity;
import fr.capeb.backend.riskevaluator.repository.MetierRepository;
import fr.capeb.backend.riskevaluator.repository.QuestionRepository;
import fr.capeb.backend.riskevaluator.repository.QuestionnaireRepository;
import fr.capeb.backend.riskevaluator.service.interfaces.CategorieQuestionService;
import fr.capeb.backend.riskevaluator.service.interfaces.PreconisationGlobaleService;
import fr.capeb.backend.riskevaluator.service.interfaces.QuestionnaireService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Transactional
public class QuestionnaireServiceImpl implements QuestionnaireService {

    @Autowired
    private QuestionnaireRepository questionnairesRepo;

    @Autowired
    private MetierRepository pMetierRepository;

    @Autowired
    private QuestionRepository pQuestionRepository;

    @Autowired
    private CategorieQuestionService pCategorieQuestionManager;

    @Autowired
    private PreconisationGlobaleService pPreconisationGlobaleManager;

    @Autowired
    private ModelMapper modelMapper;

    public List<Questionnaire> getAllQuestionnaires() {

        var wQuestionaireEntitys=questionnairesRepo.findAll(Sort.by(Sort.Direction.ASC,"thematique"));
        var wQuestionnaires=wQuestionaireEntitys
                .stream()
                .map(stop -> modelMapper.map(stop, Questionnaire.class))
                .collect(Collectors.toList());
//        wQuestionnaires.stream().forEach(questionnaire -> {
//            var wCategorieQuestion=pCategorieQuestionRepo
//                    .findByQuestionaire(questionnaire.getIdQuestionnaire()).stream()
//                    .map(stop -> modelMapper.map(stop, CategorieQuestion.class))
//                    .collect(Collectors.toList());
//
//            var wPreconisationGlobale=pPreconisationGlobaleRepo.findByQuestionaire(questionnaire.getIdQuestionnaire()).stream()
//                    .map(stop -> modelMapper.map(stop, PreconisationGlobale.class))
//                    .collect(Collectors.toList());
//
//
//            if(!wCategorieQuestion.isEmpty()) {
//                questionnaire
//                        .getCategorieQuestions()
//                        .addAll(wCategorieQuestion);
//            }
//            if(!wPreconisationGlobale.isEmpty()) {
//                questionnaire
//                        .getPreconisationGlobales()
//                        .addAll(wPreconisationGlobale);
//            }
//        });

        return wQuestionnaires;


    }

    public Optional<Questionnaire> getQuestionnaireById(Integer quesId) {


        if(quesId == null) return Optional.empty();

        Optional<QuestionnaireEntity> questionnaire = questionnairesRepo.findById(quesId);

        if(questionnaire.isEmpty()) return Optional.empty();

        return Optional.of(modelMapper.map(questionnaire.get(),Questionnaire.class));
    }

    @Override
    public Optional<Questionnaire> createQuestionnaire(Questionnaire questionnaire) {

        var ques = Optional.of(modelMapper.map(questionnaire, QuestionnaireEntity.class)).orElseThrow(MappingDataException::new);
        var updated = Optional.of(questionnairesRepo.save(ques)).orElseThrow(CreateOrUpdateException::new);
        return Optional.of(modelMapper.map(updated,Questionnaire.class));

    }
    @Override
    public Optional<Questionnaire> UpdateQuestionnaire(Questionnaire questionnaire) {

        var ques = questionnairesRepo.getById(questionnaire.getIdQuestionnaire());
        ques.setThematique(questionnaire.getThematique());
        var updated = Optional.of(questionnairesRepo.save(ques)).orElseThrow(CreateOrUpdateException::new);
        return Optional.of(modelMapper.map(updated,Questionnaire.class));

    }

    @Override
    public Optional<Object> deleteQuestionnaire(Integer quesId) {
        var wQuestionnaireEntity=questionnairesRepo.getById(quesId);
        var wCategorieQuestionIdsToRemove=wQuestionnaireEntity.getCategorieQuestions().stream().map(wCategorieQuestion->wCategorieQuestion.getIdCategorie()).collect(Collectors.toList());
        wCategorieQuestionIdsToRemove.forEach(wCategorieQuestionIdToRemove->pCategorieQuestionManager.deleteCategorieQuestion(wCategorieQuestionIdToRemove));
        var wPreconisationGlobaleIdsToRemove= wQuestionnaireEntity.getPreconisationGlobales().stream().map(wPreconisationGlobale->wPreconisationGlobale.getIdPreconisationG()).collect(Collectors.toList());
        wPreconisationGlobaleIdsToRemove.forEach(wPreconisationGlobaleIdToRemove->pPreconisationGlobaleManager.deletePreconisationGlobale(wPreconisationGlobaleIdToRemove));
        questionnairesRepo.delete(wQuestionnaireEntity);
        return Optional.empty();
    }

    @Override
    public List<Metier> getMetiersByQuestionnaireId(Integer aQuestionnaireId) {

        return pMetierRepository.getMetiersByQuestionnaireId(aQuestionnaireId).stream()
                .map(stop -> modelMapper.map(stop, Metier.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CategorieQuestion> getCategorieQuestionsByQuestionnaireIdAndMetiers(Integer aQuestionnaireId, Set<Integer> metierIds) {
        var wMetiers=pMetierRepository.findAllById(metierIds).stream().map(metier->modelMapper.map(metier,Metier.class)).collect(Collectors.toList());
        return  pQuestionRepository.getCategorieQuestionsByQuestionnaireIdAndMetiers(aQuestionnaireId, metierIds)
                .stream()
                .map(categorieQuestion -> {
                    var wCategorieQuestion=modelMapper.map(categorieQuestion, CategorieQuestion.class);
                    wCategorieQuestion.setQuestions(wCategorieQuestion.getQuestions().stream().filter(question -> question.getMetiers().containsAll(wMetiers)).collect(Collectors.toList()));
                    return wCategorieQuestion;
                })
                .collect(Collectors.toList());
    }


}
