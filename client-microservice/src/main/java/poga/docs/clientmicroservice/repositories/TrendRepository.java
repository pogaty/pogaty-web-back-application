package poga.docs.clientmicroservice.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import poga.docs.clientmicroservice.models.Trend;

public interface TrendRepository extends CrudRepository<Trend, Long> {
    public List<Trend> findAll();

    @Query("SELECT t FROM Trend t WHERE t.client.id = :clientId AND t.problem.id = :problemId")
    Optional<Trend> findByClientIdAndProblemId(@Param("clientId") Long clientId, @Param("problemId") Long problemId);

    @Query("SELECT COUNT(t) FROM Trend t WHERE t.problem.id = :problemId AND t.trend = true")
    Integer findCountByProblemIdTrendUp(@Param("problemId") Long problemId);

    @Query("SELECT COUNT(t) FROM Trend t WHERE t.problem.id = :problemId AND t.trend = false")
    Integer findCountByProblemIdTrendDown(@Param("problemId") Long problemId);
}
