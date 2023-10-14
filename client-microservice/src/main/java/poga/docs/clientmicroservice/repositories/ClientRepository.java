package poga.docs.clientmicroservice.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import poga.docs.clientmicroservice.models.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {
    public List<Client> findAll();
}
