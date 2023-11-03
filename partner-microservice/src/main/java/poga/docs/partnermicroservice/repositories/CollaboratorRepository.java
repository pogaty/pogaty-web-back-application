package poga.docs.partnermicroservice.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import poga.docs.partnermicroservice.models.Collaborator;

public interface CollaboratorRepository extends CrudRepository<Collaborator, Long> {
    public List<Collaborator> findAll();
    public List<Collaborator> findByNameStartingWith(String prefix);
    public Optional<Collaborator> findByfileImage(String fileImage);

}
