package poga.docs.partnermicroservice.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import poga.docs.partnermicroservice.models.Collaborator;
import poga.docs.partnermicroservice.models.Services;

public interface CollaboratorRepository extends CrudRepository<Collaborator, Long> {
    public List<Collaborator> findAll();
    public Optional<Collaborator> findById(Long id);
    public Optional<Collaborator> findByName(String name);
    public Optional<Collaborator> findByfileImage(String fileImage);

    @Query("SELECT s FROM Collaborator c JOIN c.service s WHERE c.id = :collabId")
    public List<Services> findServiceByCollabId(@Param("collabId") Long collabId);
}
