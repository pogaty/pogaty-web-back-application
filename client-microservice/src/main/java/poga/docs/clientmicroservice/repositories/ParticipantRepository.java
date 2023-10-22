package poga.docs.clientmicroservice.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import poga.docs.clientmicroservice.models.Participant;

public interface ParticipantRepository extends CrudRepository<Participant, Long> {
    public List<Participant> findAll();
    public List<Participant> findByRole(String role);
    public List<Participant> findByRoleStartingWith(String prefix);
}
