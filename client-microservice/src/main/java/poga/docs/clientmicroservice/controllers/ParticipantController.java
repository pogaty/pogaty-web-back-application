package poga.docs.clientmicroservice.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import poga.docs.clientmicroservice.ServiceMapper;
import poga.docs.clientmicroservice.models.Client;
import poga.docs.clientmicroservice.models.Idea;
import poga.docs.clientmicroservice.models.Participant;
import poga.docs.clientmicroservice.repositories.IdeaRepository;
import poga.docs.clientmicroservice.repositories.ParticipantRepository;
import poga.docs.clientmicroservice.services.ClientService;
import poga.docs.clientmicroservice.services.IdeaService;
import poga.docs.clientmicroservice.services.ParticipantService;

@RestController
@RequestMapping("/participants")
public class ParticipantController {
    private final ParticipantService participantService;
    private final IdeaService ideaService;
    private final ClientService clientService;
    
    private final ParticipantRepository participantRepository;
    private final IdeaRepository ideaRepository;

    @Autowired
    public ParticipantController(ParticipantService participantService, ParticipantRepository participantRepository,
    ServiceMapper clientMapper, IdeaService ideaService, ClientService clientService, IdeaRepository ideaRepository) {
        this.participantService = participantService;
        this.participantRepository = participantRepository;
        this.ideaService = ideaService;
        this.clientService =clientService;
        this.ideaRepository = ideaRepository;
    }

    //get for all list participant
    @GetMapping
    public ResponseEntity<List<Participant>> getAllParticipant() {
        List<Participant> participants = participantService.findAllPaticipant();
        return ResponseEntity.ok(participants);
    }
    
    //get data to tell Client with heahder project
    @GetMapping("/{role}")
    public ResponseEntity<?> getRole(@PathVariable String role) {
        if (role.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("role Not Found");
        }

        List<Participant> participants = participantService.findByRolePaticipant(role);
        return ResponseEntity.ok(participants);
    }

    @GetMapping("/on_idea/{idea_id}/by/{client_id}")
    public ResponseEntity<?> getByClientIdAndIdeaId(@PathVariable Long client_id, @PathVariable Long idea_id) {
        Optional<Participant> participantOpt = participantService.findByClientIdAndIdeaId(client_id, idea_id);

        if (!participantOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("it'seem this client not apart of participants.");
        }

        Participant participant = participantOpt.get();
        return ResponseEntity.ok(participant);
    }

    @GetMapping("/idea_by/{client_username}")
    public ResponseEntity<?> getIdeasByClientId(@PathVariable String client_username) {
        List<Idea> ideas = participantService.findIdeasByClientId(client_username);

        if (ideas.isEmpty()) {
            return ResponseEntity.status(204).body("no ideas of client content.");
        }

        return ResponseEntity.ok(ideas);
    }

    //Create participant can be repeated participant
    @PostMapping("/on_idea/{idea_id}/by/{client_id}")
    public ResponseEntity<String> createParticipant(@PathVariable Long idea_id, @PathVariable Long client_id,
    @RequestBody Participant participant) {
        Optional<Idea> ideaOpt = ideaService.findByIdeaId(idea_id);
        Optional<Client> clientOpt = clientService.findByClientId(client_id);

        if (!ideaOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("idea not found.");
        }
        Idea idea = ideaOpt.get();

        if (!clientOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("client not found.");
        }
        Client client = clientOpt.get();

        Participant newParticipant;
        Optional<Participant> participantOpt = participantService.findByClientIdAndIdeaId(client_id, idea_id);
        if (!participantOpt.isPresent()) {
            newParticipant = new Participant();
            newParticipant.setClient(client);
            newParticipant.getIdeas().add(idea);
        } else {
            newParticipant = participantOpt.get();
            idea.getParticipants().remove(newParticipant);
        }

        // save participant.
        newParticipant.setRole(participant.getRole());
        participantRepository.save(newParticipant);

        // save participant on idea.
        idea.getParticipants().add(newParticipant);
        ideaRepository.save(idea);
        return ResponseEntity.ok("Participant created");
    }

    @DeleteMapping("/on_idea/{idea_id}/by/{client_id}")
    public ResponseEntity<String> deleteParticipant(@PathVariable Long idea_id,@PathVariable Long client_id) {
        Optional<Idea> ideaOpt = ideaService.findByIdeaId(idea_id);
        Optional<Client> clientOpt = clientService.findByClientId(client_id);

        if (!ideaOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("idea not found.");
        }
        Idea idea = ideaOpt.get();

        if (!clientOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("client not found.");
        }

        Optional<Participant> participantOpt = participantService.findByClientIdAndIdeaId(client_id, idea_id);
        if (!participantOpt.isPresent()) { 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("tparticipant not found.");
        } else {
            Participant participant = participantOpt.get();

            // remove participant from idea repository.
            idea.getParticipants().remove(participant);
            ideaRepository.save(idea);

            // remove participant from repository.
            participantRepository.deleteById(participant.getParticipant_id());
            return ResponseEntity.ok("remove participant successfully");
        }
    }

    
}
