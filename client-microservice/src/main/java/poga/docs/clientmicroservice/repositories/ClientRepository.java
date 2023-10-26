package poga.docs.clientmicroservice.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import poga.docs.clientmicroservice.models.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {
    public List<Client> findAll();
    public Client findByUsername(String username);
    public List<Client> findByUsernameStartingWith(String prefix);
    public Optional<Client> findById(Long id);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Client c WHERE LOWER(c.username) = LOWER(:username)")
    public boolean existsByLowercaseUsername(@Param("username") String username);
}
