package fr.capeb.backend.riskevaluator.repository;

import fr.capeb.backend.riskevaluator.model.MetierQuestionEntity;
import fr.capeb.backend.riskevaluator.model.MetierQuestionEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MetierQuestionRepository extends JpaRepository<MetierQuestionEntity, MetierQuestionEntityPK> {
    @Transactional
    @Modifying
    @Query("delete from  MetierQuestionEntity metierQuestion where metierQuestion.question.idQuestion= :aQuestionId and metierQuestion.metier.idMetier= :aMetierId")
    void deleteById(@Param("aQuestionId") Integer aQuestionId,@Param("aMetierId") Integer aMetierId);
}
