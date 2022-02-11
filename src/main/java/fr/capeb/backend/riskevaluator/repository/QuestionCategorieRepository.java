package fr.capeb.backend.riskevaluator.repository;

import fr.capeb.backend.riskevaluator.model.CategorieQuestionEntity;
import fr.capeb.backend.riskevaluator.model.PreconisationGlobaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;

@Repository
public interface QuestionCategorieRepository extends JpaRepository<CategorieQuestionEntity, Integer> {


    @Query("select cq from CategorieQuestionEntity cq where cq.questionnaire.idQuestionnaire = :idQ")
    Set<CategorieQuestionEntity> findByQuestionaire(@Param("idQ") Integer idQ);

}
