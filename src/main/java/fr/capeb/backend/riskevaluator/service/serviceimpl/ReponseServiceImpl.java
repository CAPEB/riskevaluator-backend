package fr.capeb.backend.riskevaluator.service.serviceimpl;

import fr.capeb.backend.riskevaluator.dto.Reponse;
import fr.capeb.backend.riskevaluator.exceptions.model.MappingDataException;
import fr.capeb.backend.riskevaluator.model.ReponseEntity;
import fr.capeb.backend.riskevaluator.repository.ReponseRepository;
import fr.capeb.backend.riskevaluator.service.interfaces.ReponseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class  ReponseServiceImpl implements ReponseService {
    @Autowired
    private ReponseRepository pReponseRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Reponse> getAllReponses() {
        return pReponseRepository.findAll(Sort.by(Sort.Direction.ASC,"contenu"))
                .stream()
                .map(stop -> modelMapper.map(stop, Reponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Reponse> getReponseById(Integer aReponseId) {
        Optional<ReponseEntity> wResponse = pReponseRepository.findById(aReponseId);
        if(wResponse.isPresent()){
            return Optional.of(modelMapper.map(wResponse.get(),Reponse.class));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Reponse> createOrUpdateReponse(Reponse aReponse) {

        Optional<ReponseEntity> wResponseEntity = Optional.of(modelMapper.map(aReponse,ReponseEntity.class));
        if(wResponseEntity.isEmpty()){
            throw new MappingDataException();
        }
        wResponseEntity.get().getQuestion().getReponses().add(wResponseEntity.get());
        Optional<ReponseEntity> wResponseEntityUpdated = Optional.of(pReponseRepository.save(wResponseEntity.get()));
        return Optional.of(modelMapper.map(wResponseEntityUpdated.get(),Reponse.class));
    }

    @Override
    public Optional<Reponse> createReponse(Reponse aReponse) {

        Optional<ReponseEntity> wResponseEntity = Optional.of(modelMapper.map(aReponse,ReponseEntity.class));
        if(wResponseEntity.isEmpty()){
            throw new MappingDataException();
        }
        wResponseEntity.get().getQuestion().getReponses().add(wResponseEntity.get());
        return Optional.of(modelMapper.map(wResponseEntity.get(),Reponse.class));
    }

    @Override
    public Optional<Object> deleteReponse(Integer aReponseId) {
        var wReponseEntity=pReponseRepository.getById(aReponseId);
        pReponseRepository.delete(wReponseEntity);
        return Optional.empty();
    }
}
