package fr.capeb.backend.riskevaluator.service.serviceimpl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.capeb.backend.riskevaluator.dto.Metier;
import fr.capeb.backend.riskevaluator.dto.Question;
import fr.capeb.backend.riskevaluator.exceptions.model.CreateOrUpdateException;
import fr.capeb.backend.riskevaluator.exceptions.model.MappingDataException;
import fr.capeb.backend.riskevaluator.model.MetierEntity;
import fr.capeb.backend.riskevaluator.repository.MetierRepository;
import fr.capeb.backend.riskevaluator.service.interfaces.MetierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Transactional
public class MetierServiceImpl implements MetierService {

    @Autowired
    private MetierRepository metierRepo ;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Set<Metier> getAllMetier() {
            return metierRepo.findAll()
                .stream()
                .map(stop -> modelMapper.map(stop, Metier.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<Metier> getMetierById(Integer quesId) {
        if(quesId == null) return Optional.empty();


        Optional<MetierEntity> metier = metierRepo.findById(quesId);
        if (metier.isPresent()) {
            return Optional.of(modelMapper.map(metier.get(),Metier.class));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Metier> createOrUpdateMetier(Metier metier) {
        var ques = Optional.of(modelMapper.map(metier, MetierEntity.class)).orElseThrow(MappingDataException::new);
        var updated = Optional.of(metierRepo.save(ques)).orElseThrow(CreateOrUpdateException::new);
        return Optional.of(modelMapper.map(updated,Metier.class));
    }

    @Override
    public Optional<Object> deleteMetierById(Integer quesId) {
        metierRepo.deleteById(quesId);
        return Optional.empty();
    }
}
