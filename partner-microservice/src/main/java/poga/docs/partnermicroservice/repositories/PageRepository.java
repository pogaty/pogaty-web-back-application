package poga.docs.partnermicroservice.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import poga.docs.partnermicroservice.models.Page;

public interface PageRepository extends CrudRepository<Page , Long>{
    public List<Page> findAll();
    public List<Page> findByServiceStartingWith(String prefix);
    
}
