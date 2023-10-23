package poga.docs.partnermicroservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poga.docs.partnermicroservice.models.Campaign;
import poga.docs.partnermicroservice.repositories.CampaignRepository;

@Service
public class CampaignService {
    private final CampaignRepository campaignRepository;

    @Autowired
    public CampaignService(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    public List<Campaign> findAllCampaign() {
        return this.campaignRepository.findAll();
    }

    public List<Campaign> findByTopicStartingWithCampaign(String prefix){
        return this.campaignRepository.findByTopicStartingWith(prefix);
    }
}
