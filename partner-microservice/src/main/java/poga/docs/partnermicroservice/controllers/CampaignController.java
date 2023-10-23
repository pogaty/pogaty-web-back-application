package poga.docs.partnermicroservice.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poga.docs.partnermicroservice.ServiceMapper;
import poga.docs.partnermicroservice.models.Campaign;
import poga.docs.partnermicroservice.models.CampaignDTO;
import poga.docs.partnermicroservice.repositories.CampaignRepository;
import poga.docs.partnermicroservice.services.CampaignService;


@RestController
@RequestMapping("/campaigns")
public class CampaignController {
    private final CampaignRepository campaignRepository;
    private final CampaignService campaignService;
    private final ServiceMapper serviceMapper;

    @Autowired
    public CampaignController(CampaignRepository campaignRepository, CampaignService campaignService,
            ServiceMapper serviceMapper) {
        this.campaignRepository = campaignRepository;
        this.campaignService = campaignService;
        this.serviceMapper = serviceMapper;
    }

    @GetMapping()
    public ResponseEntity<?> getAllCampaigns() {
        return ResponseEntity.ok(campaignService.findAllCampaign());
    }

    @GetMapping("/{campaign_id}")
    public ResponseEntity getAllCampaignBycampaign_id(@PathVariable long campaign_id) {
        Optional<Campaign> optCampaign = campaignRepository.findById(campaign_id);
        
        // check if id exists in db
        if (!optCampaign.isPresent()) {
            // return error message 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Campaign Not Found");
        
        }
        Campaign campaigns = optCampaign.get();
        return ResponseEntity.ok(campaigns);
    }

     @GetMapping("/search/{topic}")
    public ResponseEntity<?> getNameStartingWithCampaign(@PathVariable String topic) {
        if (topic.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Campaign Not Found");
        }

        List<Campaign> campaigns = campaignService.findByTopicStartingWithCampaign(topic);
        return ResponseEntity.ok(campaigns);
    }

    @PostMapping
    public ResponseEntity<String> createCampaign(@RequestBody Campaign campaign) {
        campaignRepository.save(campaign);
        return ResponseEntity.ok("Campaign created");
    }

    @PatchMapping("/{campaign_id}")
    public ResponseEntity<String> partialUpdateCampaign(@PathVariable Long campaign_id, @RequestBody CampaignDTO campaignDTO) {
        Optional<Campaign> optCampaign = campaignRepository.findById(campaign_id);
        if (!optCampaign.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Campaign not found");
        }

        Campaign campaigns = optCampaign.get();
        serviceMapper.updateCampaignFromDto(campaignDTO, campaigns);
        campaignRepository.save(campaigns);
        return ResponseEntity.ok("Campaign updated");
    }

    @DeleteMapping("/{campaign_id}")
    public ResponseEntity<String> deleteCampaign(@PathVariable Long campaign_id) {
        if (!campaignRepository.existsById(campaign_id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Campaign not found");
        }

        campaignRepository.deleteById(campaign_id);
        return ResponseEntity.ok("Campaign deleted");
    }
}
