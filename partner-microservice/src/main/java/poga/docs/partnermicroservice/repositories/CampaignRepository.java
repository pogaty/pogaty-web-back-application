package poga.docs.partnermicroservice.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import poga.docs.partnermicroservice.models.Campaign;

public interface CampaignRepository extends CrudRepository<Campaign, Long>{
    public List<Campaign> findAll();
    public List<Campaign> findByTopicStartingWith(String prefix);
}
