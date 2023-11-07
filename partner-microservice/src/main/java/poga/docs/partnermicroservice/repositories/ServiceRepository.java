package poga.docs.partnermicroservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import poga.docs.partnermicroservice.models.Services;

public interface ServiceRepository extends CrudRepository<Services,Long>{
    public List<Services> findAll();
    public List<Services> findByNameStartingWith(String prefix);
    
    // @Query("SELECT s FROM Services s WHERE s.serviceType = :serviceType")
    public List<Services> findByServiceType(String type);

    // @Query("SELECT s FROM Services s WHERE s.serviceType = :serviceType AND s.category = :category")
    public List<Services> findByServiceTypeAndCategory(String type, String category);
}
