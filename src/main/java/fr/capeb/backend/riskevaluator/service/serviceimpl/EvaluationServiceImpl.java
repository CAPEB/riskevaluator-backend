package fr.capeb.backend.riskevaluator.service.serviceimpl;

import fr.capeb.backend.riskevaluator.dto.Evaluation;
import fr.capeb.backend.riskevaluator.model.EvaluationEntity;
import fr.capeb.backend.riskevaluator.repository.EvaluationRepository;
import fr.capeb.backend.riskevaluator.service.interfaces.EvaluationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component

public class EvaluationServiceImpl implements EvaluationService {
    @Autowired
    private EvaluationRepository pEvaluationRepository;

    @Autowired
    private ModelMapper pModelMapper;

    @Override
    public List<Evaluation> getAllEvaluation() {
        return pEvaluationRepository.findAll(Sort.by(Sort.Direction.ASC,"id_evaluation")).stream()
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
        var xEvaluationEntity = pModelMapper.map(aEvaluation, EvaluationEntity.class);
        var wEvaluationEntity=pEvaluationRepository.save(xEvaluationEntity);
        return Optional.of(pModelMapper.map(wEvaluationEntity,Evaluation.class));
    }

}
