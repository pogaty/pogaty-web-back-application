package poga.docs.clientmicroservice.controllers;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poga.docs.clientmicroservice.ServiceMapper;
import poga.docs.clientmicroservice.models.Agreement;
import poga.docs.clientmicroservice.models.Client;
import poga.docs.clientmicroservice.models.Idea;
import poga.docs.clientmicroservice.models.IdeaDTO;
import poga.docs.clientmicroservice.repositories.AgreementRepository;
import poga.docs.clientmicroservice.repositories.IdeaRepository;
import poga.docs.clientmicroservice.services.AgreementService;
import poga.docs.clientmicroservice.services.ClientService;
import poga.docs.clientmicroservice.services.IdeaService;

@RestController
@RequestMapping("/ideas")
public class IdeaController {
    private final IdeaService ideaService;
    private final ClientService clientService;
    private final AgreementService agreementService;

    private final IdeaRepository ideaRepository;
    private final AgreementRepository agreementRepository;

    private final ServiceMapper serviceMapper;

    @Autowired
    IdeaController(IdeaService ideaService, IdeaRepository ideaRepository,ServiceMapper serviceMapper,
    ClientService clientService, AgreementRepository agreementRepository, AgreementService agreementService) {
        this.ideaService = ideaService;
        this.ideaRepository = ideaRepository;
        this.serviceMapper = serviceMapper;
        this.clientService = clientService;
        this.agreementRepository = agreementRepository;
        this.agreementService = agreementService;
    }

    // i dont khow
    @GetMapping
    public ResponseEntity<List<Idea>> getAllIdea() {
        List<Idea> idea = ideaService.findAllIdea();
        return ResponseEntity.ok(idea);
    }

    // 
    @GetMapping("/{ideaHeader}")
    public ResponseEntity<?> getUsernameIdea(@PathVariable String ideaHeader) {
        Idea ideas = ideaRepository.findByIdeaHeader(ideaHeader);
        
        if (ideas == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Idea Not Found");
        }

        return ResponseEntity.ok(ideas);
    }

    //for search bar to find Idea by ideaHeader
    @GetMapping("/search/{ideaHeader}")
    public ResponseEntity<?> getUsernameStartingWithClients(@PathVariable String ideaHeader) {
        if (ideaHeader.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ideaHeader Not Found");
        }

        List<Idea> Ideas = ideaService.findByHeaderIdeaStartingWith(ideaHeader);
        return ResponseEntity.ok(Ideas);
    }

    //Create idea can be repeated idea
    @PostMapping
    public ResponseEntity<String> createIdea(@RequestBody Idea idea) {
        ideaRepository.save(idea);
        return ResponseEntity.ok("Idea created");
    }

    //Update idea by handle
    @PutMapping("/{idea_id}")
    public ResponseEntity<String> updateIdea(@PathVariable Long idea_id, @RequestBody Idea idea) {
        if (!ideaRepository.existsById(idea_id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Idea not found");
        }
        
        idea.setIdea_id(idea_id); // Ensure the idea_id is set
        ideaRepository.save(idea);
        return ResponseEntity.ok("Idea updated");
    }

    @PutMapping("/{idea_id}/reaction_by/{client_id}")
    public ResponseEntity<String> updateReactionByClientOnEachIdea(
        @PathVariable Long idea_id, @PathVariable Long client_id, @RequestBody Agreement agreement) {
            Optional<Client> clientOpt = clientService.findByClientId(client_id);
            Optional<Idea> ideaOpt = ideaService.findByIdeaId(idea_id);

            // does client exist.
            if (!clientOpt.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
            }
            Client client = clientOpt.get();

            // does idea exist.
            if (!ideaOpt.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Idea not found.");
            }
            Idea idea = ideaOpt.get();

            Agreement agreed = new Agreement();
            Optional<Agreement> agreementOpt = agreementService.findByReactionFactor(client_id, idea_id);
            if (!agreementOpt.isPresent()) {
                agreed.setClient(client);
            } else {
                agreed = agreementOpt.get();
            }

            agreed.setAgreed(agreement.isAgreed());
            idea.getAgreements().add(agreed);
            agreementRepository.save(agreed);
            return ResponseEntity.ok("reaction updated.");
        }

    //Update idea by specific parameter
    @PatchMapping("/{idea_id}")
    public ResponseEntity<String> partialUpdateIdea(@PathVariable Long idea_id, @RequestBody IdeaDTO ideaDTO) {
        Optional<Idea> optIdea = ideaRepository.findById(idea_id);
        if (!optIdea.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Idea not found");
        }

        Idea idea = optIdea.get();
        serviceMapper.updateIdeaFromDto(ideaDTO, idea);
        ideaRepository.save(idea);
        return ResponseEntity.ok("Idea updated");
    }

    @DeleteMapping("/{idea_id}")
    public ResponseEntity<String> deleteIdea(@PathVariable Long idea_id) {
        if (!ideaRepository.existsById(idea_id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Idea not found");
        }

        ideaRepository.deleteById(idea_id);
        return ResponseEntity.ok("Idea deleted");
    }

    
}
