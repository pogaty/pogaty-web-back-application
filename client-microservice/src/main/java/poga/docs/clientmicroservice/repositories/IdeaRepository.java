package poga.docs.clientmicroservice.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import poga.docs.clientmicroservice.models.Client;
import poga.docs.clientmicroservice.models.Idea;

public interface IdeaRepository extends CrudRepository<Idea, Long>{
    public List<Idea> findAll();
    public Idea findByIdeaHeader(String ideaHeader);
    public List<Idea> findByIdeaHeaderStartingWith(String prefix);
    public Optional<Idea> findById(Long id);
    
    @Query("SELECT p.client FROM Idea i JOIN i.participants p WHERE i.id = :ideaId AND p.client.id != :clientId")
    public List<Client> findClientsPrticipateOnIdea(@Param("ideaId") Long ideaId, @Param("clientId") Long clientId);
}
