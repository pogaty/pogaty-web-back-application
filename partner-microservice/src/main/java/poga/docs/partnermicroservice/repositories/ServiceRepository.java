package poga.docs.partnermicroservice.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import poga.docs.partnermicroservice.models.Service;

public interface ServiceRepository extends CrudRepository<Service,Long>{
    public List<Service> findAll();
    public List<Service> findByNameStartingWith(String prefix);
    
}
