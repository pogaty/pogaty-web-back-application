package poga.docs.clientmicroservice.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import poga.docs.clientmicroservice.models.Problem;
import poga.docs.clientmicroservice.models.Trend;

public interface ProblemRepository extends CrudRepository<Problem, Long>{
    public List<Problem> findAll();

    public List<Problem> findByTopic(String topic);
    public List<Problem> findByCategory(String category);
    public Optional<Problem> findById(Long id);
    List<Problem> findByTopicStartingWith(String prefix);

    @Query("SELECT p FROM Problem p JOIN p.marks c WHERE c.id = :client_id")
    List<Problem> findByMarksClientId(@Param("client_id") Long client_id);

    @Query("SELECT p FROM Problem p JOIN p.client c WHERE c.id = :client_id")
    List<Problem> findByClientClient_Id(@Param("client_id") Long client_id);

    @Query("SELECT p FROM Problem p JOIN p.marks c WHERE c.id = :client_id AND p.id = :problem_id")
    Optional<Problem> findByMarksFactor(@Param("client_id") Long client_id, @Param("problem_id") Long problem_id);

    @Query("SELECT t FROM Problem p JOIN p.trends t WHERE p.id = :problemId")
    List<Trend> findTrendByProblemId(@Param("problemId") Long problemId);
}
