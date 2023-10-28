package poga.docs.clientmicroservice.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import poga.docs.clientmicroservice.models.Agreement;

public interface AgreementRepository extends JpaRepository<Agreement, Long> {

    public List<Agreement> findAll();

    @Query("SELECT a FROM Agreement a WHERE a.client.id = :clientId AND a.idea.id = :ideaId")
    Optional<Agreement> findByClientIdAndIdeaId(@Param("clientId") Long clientId, @Param("ideaId") Long ideaId);

    @Query("SELECT a FROM Agreement a WHERE a.idea.id = :ideaId AND a.agreed = true")
    List<Agreement> findByIdeaIdAgree( @Param("ideaId") Long ideaId);

    @Query("SELECT a FROM Agreement a WHERE a.idea.id = :ideaId AND a.agreed = false")
    List<Agreement> findByIdeaIdDisagree( @Param("ideaId") Long ideaId);
}
