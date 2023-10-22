package poga.docs.clientmicroservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import poga.docs.clientmicroservice.models.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {
    public List<Client> findAll();
    public List<Client> findByUsername(String username);
    List<Client> findByUsernameStartingWith(String prefix);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Client c WHERE LOWER(c.username) = LOWER(:username)")
    boolean existsByLowercaseUsername(@Param("username") String username);
}
