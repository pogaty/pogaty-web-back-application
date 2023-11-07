package poga.docs.partnermicroservice.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import poga.docs.partnermicroservice.models.Services;

public interface ServiceRepository extends CrudRepository<Services,Long>{
    public List<Services> findAll();
    public List<Services> findByNameStartingWith(String prefix);
    
}
