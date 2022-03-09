package fr.capeb.backend.riskevaluator.service.serviceimpl;

import fr.capeb.backend.riskevaluator.dto.Entreprise;
import fr.capeb.backend.riskevaluator.dto.Evaluation;
import fr.capeb.backend.riskevaluator.model.*;
import fr.capeb.backend.riskevaluator.repository.EntrepriseRepository;
import fr.capeb.backend.riskevaluator.repository.EvaluationRepository;
import fr.capeb.backend.riskevaluator.repository.ScoreCategorieRepository;
import fr.capeb.backend.riskevaluator.service.interfaces.CategorieQuestionService;
import fr.capeb.backend.riskevaluator.service.interfaces.EvaluationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component

public class EvaluationServiceImpl implements EvaluationService {
    @Autowired
    private EvaluationRepository pEvaluationRepository;

    @Autowired
    private CategorieQuestionService pCategorieQuestionManager;

    @Autowired
    private ScoreCategorieRepository pScoreCategorieRepository;

    @Autowired
    private EntrepriseRepository pEntrepriseRepository;

    @Autowired
    private ModelMapper pModelMapper;

    @Override
    public List<Evaluation> getAllEvaluation() {
        return pEvaluationRepository.findAll(Sort.by(Sort.Direction.ASC,"idEvaluation")).stream()
                .map(aEvaluationEntity -> pModelMapper.map(aEvaluationEntity,Evaluation.class))
                .collect(Collectors.toList());

    }

    @Override
    public Optional<Evaluation> getEvaluationById(Integer aEvaluationId) {
        if(aEvaluationId == null) return Optional.empty();
        var aEvaluationEntity=pEvaluationRepository.findById(aEvaluationId);
        if(aEvaluationEntity.isPresent()){
            return Optional.of(pModelMapper.map(aEvaluationEntity,Evaluation.class));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Object> deleteEvaluation(Integer aEvaluationId) {
        var aEvaluationEntity=pEvaluationRepository.findById(aEvaluationId);
        if(aEvaluationEntity.isPresent()){
            pEvaluationRepository.deleteById(aEvaluationId);
            return Optional.empty();
        }
        throw new NotFoundException("Evaluation not found");
    }

    @Override
    public Optional<Evaluation> SaveEvaluation(Evaluation aEvaluation) {
        aEvaluation.setIdEvaluation(-1);
        var aEntrepriseEntity=pEntrepriseRepository.findById(aEvaluation.getEntreprise().getNoSiret());
        EntrepriseEntity wEntrepriseEntity;
        if(aEntrepriseEntity.isPresent()){
            wEntrepriseEntity=aEntrepriseEntity.get();
        }
        else{
            wEntrepriseEntity = pEntrepriseRepository.saveAndFlush(pModelMapper.map(aEvaluation.getEntreprise(), EntrepriseEntity.class));
        }


        var wEvaluationEntity = pModelMapper.map(aEvaluation, EvaluationEntity.class);
        wEvaluationEntity.setEntreprise(wEntrepriseEntity);
        wEvaluationEntity.setScoreCategories(new HashSet<>());
        var wEvaluationSavedEntity=pEvaluationRepository.saveAndFlush(wEvaluationEntity);

        aEvaluation.getScoreCategories().forEach(scoreCategory -> {
            var wScoreCategoryEntity=pModelMapper.map(scoreCategory, ScoreCategoryEntity.class);
            wScoreCategoryEntity.setEvaluation(wEvaluationSavedEntity);
            wScoreCategoryEntity.setKey(new ScoreCategoryEntityPK(wEvaluationEntity.getIdEvaluation(),scoreCategory.getCategorieQuestion().getIdCategorie()));
            wEvaluationSavedEntity.getScoreCategories().add(wScoreCategoryEntity);
            wEvaluationEntity.getScoreCategories().add(wScoreCategoryEntity);

        });
        var wEvaluationUpdatedEntity=pEvaluationRepository.saveAndFlush(wEvaluationSavedEntity);
        return Optional.of(pModelMapper.map(wEvaluationUpdatedEntity,Evaluation.class));
    }

}
