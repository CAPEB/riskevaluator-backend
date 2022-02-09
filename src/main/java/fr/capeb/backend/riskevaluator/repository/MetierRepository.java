package fr.capeb.backend.riskevaluator.repository;


import fr.capeb.backend.riskevaluator.model.MetierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetierRepository extends JpaRepository<MetierEntity, Integer> {

    @Query("select distinct metierQuestion.metier from  MetierQuestionEntity metierQuestion where metierQuestion.question.categorieQuestion.questionnaire.idQuestionnaire = :aQuestionnaireId")
    List<MetierEntity> getMetiersByQuestionnaireId(@Param("aQuestionnaireId") Integer aQuestionnaireId);
}
