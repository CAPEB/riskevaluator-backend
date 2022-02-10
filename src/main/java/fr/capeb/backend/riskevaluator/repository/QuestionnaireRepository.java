package fr.capeb.backend.riskevaluator.repository;

import fr.capeb.backend.riskevaluator.dto.Questionnaire;
import fr.capeb.backend.riskevaluator.model.CategorieQuestionEntity;
import fr.capeb.backend.riskevaluator.model.QuestionnaireEntity;
import fr.capeb.backend.riskevaluator.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionnaireRepository extends JpaRepository<QuestionnaireEntity, Integer> {


    @Query("select cnt from QuestionnaireEntity cnt where cnt.thematique = :tm")
    Optional<QuestionnaireEntity> findByThematique(@Param("tm") String tm);
    @Query("select distinct categorieQuestion.questionnaire from  MetierQuestionEntity metierQuestion join CategorieQuestionEntity categorieQuestion " +
            "on metierQuestion.question.categorieQuestion.idCategorie=categorieQuestion.idCategorie " +
            "where metierQuestion.metier.idMetier in (:metierIds)")
    List<QuestionnaireEntity> getQuestionnaireByMetiersIds( @Param("metierIds") List<Integer> metierIds);


}
