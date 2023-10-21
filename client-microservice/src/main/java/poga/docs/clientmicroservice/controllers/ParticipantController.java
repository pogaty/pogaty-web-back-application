package poga.docs.clientmicroservice.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poga.docs.clientmicroservice.ServiceMapper;

import poga.docs.clientmicroservice.models.Participant;
import poga.docs.clientmicroservice.models.ParticipantDTO;
import poga.docs.clientmicroservice.repositories.ParticipantRepository;
import poga.docs.clientmicroservice.services.ParticipantService;

@RestController
@RequestMapping("/participants")
public class ParticipantController {
    private final ParticipantService participantService;
    private final ParticipantRepository participantRepository;
    private final ServiceMapper clientMapper;

    @Autowired
    public ParticipantController(ParticipantService participantService, ParticipantRepository participantRepository,
    ServiceMapper clientMapper) {
        this.participantService = participantService;
        this.participantRepository = participantRepository;
        this.clientMapper = clientMapper;
    }

    @GetMapping
    public ResponseEntity<List<Participant>> getAllClients() {
        List<Participant> participants = participantService.findAllPaticipant();
        return ResponseEntity.ok(participants);
    }

    @GetMapping("/search/{role}")
    public ResponseEntity<?> getUsernameClients(@PathVariable String role) {
        if (role.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("role Not Found");
        }

        List<Participant> participants = participantService.findByRolePaticipant(role);
        return ResponseEntity.ok(participants);
    }

    // @GetMapping("/searchStarting/{username}")
    // public ResponseEntity<?> getUsernameStartingWithClients(@PathVariable String username) {
    //     if (username.isEmpty()) {
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client Not Found");
    //     }

    //     List<Client> clients = participantService.findByUsernameStartingWithClients(username);
    //     return ResponseEntity.ok(clients);
    // }

    @PostMapping
    public ResponseEntity<String> createClient(@RequestBody Participant participant) {
        participantRepository.save(participant);
        return ResponseEntity.ok("Participant created");
    }

    @PutMapping("/{participant_id}")
    public ResponseEntity<String> updateClient(@PathVariable Long participant_id, @RequestBody Participant participant) {
        if (!participantRepository.existsById(participant_id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Participant not found");
        }
        
        participant.setParticipant_id(participant_id); // Ensure the participant_id is set
        participantRepository.save(participant);
        return ResponseEntity.ok("Participant updated");
    }

    @DeleteMapping("/{participant_id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long participant_id) {
        if (!participantRepository.existsById(participant_id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Participant not found");
        }

        participantRepository.deleteById(participant_id);
        return ResponseEntity.ok("Participant deleted");
    }

    @PatchMapping("/{participant_id}")
    public ResponseEntity<String> partialUpdateClient(@PathVariable Long participant_id, @RequestBody ParticipantDTO participantDTO) {
        Optional<Participant> optParticipant = participantRepository.findById(participant_id);
        if (!optParticipant.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Participant not found");
        }

        Participant participant = optParticipant.get();
        clientMapper.updateParticipatFromDto(participantDTO, participant);
        participantRepository.save(participant);
        return ResponseEntity.ok("Participant updated");
    }
}
