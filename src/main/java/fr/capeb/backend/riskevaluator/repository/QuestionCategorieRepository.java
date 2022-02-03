package fr.capeb.backend.riskevaluator.repository;

import fr.capeb.backend.riskevaluator.dto.CategorieQuestion;
import fr.capeb.backend.riskevaluator.model.CategorieQuestionEntity;
import fr.capeb.backend.riskevaluator.model.QuestionnaireEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionCategorieRepository extends JpaRepository<CategorieQuestionEntity, Integer> {


    @Query("select cq from CategorieQuestionEntity cq where cq.idQuestionnaire = :idQ and cq.idCategorie = :idC")
    Optional<CategorieQuestionEntity> findByQuestionaire(@Param("idQ") Integer idQ, @Param("idC") Integer idC);

}
