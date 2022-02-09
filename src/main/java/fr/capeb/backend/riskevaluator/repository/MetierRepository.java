package fr.capeb.backend.riskevaluator.repository;

import fr.capeb.backend.riskevaluator.model.MetierEntity;
import fr.capeb.backend.riskevaluator.model.MetierQuestionEntityPK;
import fr.capeb.backend.riskevaluator.model.PreconisationCategorieEntity;
import fr.capeb.backend.riskevaluator.model.QuestionnaireEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MetierRepository extends JpaRepository<MetierEntity, Integer> {

    @Query("select cnt from QuestionnaireEntity cnt where cnt.thematique = :tm")
    Optional<QuestionnaireEntity> metierByQuestionnaireId(@Param("") Integer aQuestionnaireId);
}
