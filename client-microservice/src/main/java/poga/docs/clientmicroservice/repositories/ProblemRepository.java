package poga.docs.clientmicroservice.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import poga.docs.clientmicroservice.models.Problem;

public interface ProblemRepository extends CrudRepository<Problem, Long>{
    public List<Problem> findAll();
    public List<Problem> findByTopic(String topic);
    public List<Problem> findByCategory(String category);
    public Optional<Problem> findById(Long id);
    List<Problem> findByTopicStartingWith(String prefix);
}
