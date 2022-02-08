package fr.capeb.backend.riskevaluator.repository;

import fr.capeb.backend.riskevaluator.model.CategorieQuestionEntity;
import fr.capeb.backend.riskevaluator.model.PreconisationCategorieEntity;
import fr.capeb.backend.riskevaluator.model.PreconisationGlobaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PreconisationGlobaleRepository extends JpaRepository<PreconisationGlobaleEntity, Integer> {

    @Query("select pg from PreconisationGlobaleEntity pg where pg.questionnaire.idQuestionnaire = :idQ")
    Collection<PreconisationGlobaleEntity> findByQuestionaire(@Param("idQ") Integer idQ);
}
