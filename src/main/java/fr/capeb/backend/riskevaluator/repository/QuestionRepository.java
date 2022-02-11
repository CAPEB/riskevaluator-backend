package fr.capeb.backend.riskevaluator.repository;


import fr.capeb.backend.riskevaluator.model.CategorieQuestionEntity;
import fr.capeb.backend.riskevaluator.model.QuestionEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer> {
    @Query("select distinct categorieQuestion from  MetierQuestionEntity metierQuestion join CategorieQuestionEntity categorieQuestion on metierQuestion.question.categorieQuestion.idCategorie=categorieQuestion.idCategorie where metierQuestion.question.categorieQuestion.questionnaire.idQuestionnaire = (:aQuestionnaireId) and metierQuestion.metier.idMetier in (:metierIds)")
    Set<CategorieQuestionEntity> getQuestionsByQuestionnaireIdAndMetiers(@Param("aQuestionnaireId") Integer aQuestionnaireId, @Param("metierIds") Set<Integer> metierIds);

}
