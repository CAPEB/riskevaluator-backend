package fr.capeb.backend.riskevaluator.repository;

import fr.capeb.backend.riskevaluator.model.MetierEntity;
import fr.capeb.backend.riskevaluator.model.QuestionEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer> {
    @Query("select distinct metierQuestion.question from  MetierQuestionEntity metierQuestion where metierQuestion.question.categorieQuestion.questionnaire.idQuestionnaire = (:aQuestionnaireId) and metierQuestion.metier.idMetier in (:metierIds)")
    List<QuestionEntity> getQuestionsByQuestionnaireIdAndMetiers(@Param("aQuestionnaireId") Integer aQuestionnaireId,@Param("metierIds") List<Integer> metierIds);

}
