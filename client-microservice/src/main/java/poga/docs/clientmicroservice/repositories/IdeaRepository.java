package poga.docs.clientmicroservice.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import poga.docs.clientmicroservice.models.Idea;

public interface IdeaRepository extends CrudRepository<Idea, Long>{
    public List<Idea> findAll();
    public Idea findByIdeaHeader(String ideaHeader);
    public List<Idea> findByIdeaHeaderStartingWith(String prefix);
}
