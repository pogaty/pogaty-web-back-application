package poga.docs.clientmicroservice.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import poga.docs.clientmicroservice.models.Problem;

public interface ProblemRepository extends CrudRepository<Problem, Long>{
    public List<Problem> findAll();
    public List<Problem> findByTopic(String topic);
    List<Problem> findByTopicStartingWith(String prefix);
}
