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

    //for search bar search role 
    @GetMapping("/search/{role}")
    public ResponseEntity<?> getSearchRole(@PathVariable String role) {
        if (role.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("role Not Found");
        }

        List<Participant> participants = participantService.findByRolePaticipantStartingWith(role);
        return ResponseEntity.ok(participants);
    }

    //Create participant can be repeated participant
    @PostMapping
    public ResponseEntity<String> createParticipant(@RequestBody Participant participant) {
        participantRepository.save(participant);
        return ResponseEntity.ok("Participant created");
    }

    //Update participant by Participant
    @PutMapping("/{participant_id}")
    public ResponseEntity<String> updateClient(@PathVariable Long participant_id, @RequestBody Participant participant) {
        if (!participantRepository.existsById(participant_id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Participant not found");
        }
        
        participant.setParticipant_id(participant_id); // Ensure the participant_id is set
        participantRepository.save(participant);
        return ResponseEntity.ok("Participant updated");
    }

    //Update participant by specific parameter
    @PatchMapping("/{participant_id}")
    public ResponseEntity<String> partialUpdateParticipant(@PathVariable Long participant_id, @RequestBody ParticipantDTO participantDTO) {
        Optional<Participant> optParticipant = participantRepository.findById(participant_id);
        if (!optParticipant.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Participant not found");
        }

        Participant participant = optParticipant.get();
        clientMapper.updateParticipatFromDto(participantDTO, participant);
        participantRepository.save(participant);
        return ResponseEntity.ok("Participant updated");
    }

    
    @DeleteMapping("/{participant_id}")
    public ResponseEntity<String> deleteParticipant(@PathVariable Long participant_id) {
        if (!participantRepository.existsById(participant_id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Participant not found");
        }

        participantRepository.deleteById(participant_id);
        return ResponseEntity.ok("Participant deleted");
    }

    
}
