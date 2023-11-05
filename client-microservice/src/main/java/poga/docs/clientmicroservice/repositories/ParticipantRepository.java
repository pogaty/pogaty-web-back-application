package poga.docs.clientmicroservice.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import poga.docs.clientmicroservice.models.Idea;
import poga.docs.clientmicroservice.models.Participant;

public interface ParticipantRepository extends CrudRepository<Participant, Long> {
    public List<Participant> findAll();
    public List<Participant> findByRole(String role);
    public List<Participant> findByRoleStartingWith(String prefix);
    public Optional<Participant> findById(Long id);

    @Query("SELECT p FROM Participant p JOIN p.client c JOIN p.ideas i WHERE c.id = :clientId AND i.id = :ideaId")
    public Optional<Participant> findByClientIdAndIdeaid(@Param("clientId") Long clientId, @Param("ideaId") Long ideaId);

    @Query("SELECT i FROM Participant p JOIN p.ideas i JOIN p.client c WHERE c.username = :username")
    public List<Idea> findIdeaByClientId(@Param("username") String username);
}
