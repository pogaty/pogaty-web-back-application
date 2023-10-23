package poga.docs.partnermicroservice.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.Problem;
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
import poga.docs.partnermicroservice.models.Collaborator;
import poga.docs.partnermicroservice.models.CollaboratorDTO;
import poga.docs.partnermicroservice.repositories.CollaboratorRepository;
import poga.docs.partnermicroservice.services.CollaboratorService;

@RestController
@RequestMapping("/collabs")
public class CollaboratorController {
    private final CollaboratorRepository collaboratorRepository;
    private final CollaboratorService collaboratorService;
    private final ServiceMapper serviceMapper;

    @Autowired
    public CollaboratorController(CollaboratorRepository collaboratorRepository,
            CollaboratorService collaboratorService, ServiceMapper serviceMapper) {
        this.collaboratorRepository = collaboratorRepository;
        this.collaboratorService = collaboratorService;
        this.serviceMapper = serviceMapper;
    }

    @GetMapping()
    public ResponseEntity<?> getAllCollaborators() {
        return ResponseEntity.ok(collaboratorService.findAllCollaborators());
    }

    @GetMapping("/{collaborator_id}")
    public ResponseEntity getAllColloabratorByCollaborator_id(@PathVariable long collaborator_id) {
        Optional<Collaborator> optCollaborator = collaboratorRepository.findById(collaborator_id);
        
        // check if id exists in db
        if (!optCollaborator.isPresent()) {
            // return error message 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Collaborator Not Found");
        
        }
        Collaborator collaborators = optCollaborator.get();
        return ResponseEntity.ok(collaborators);
    }

     @GetMapping("/search/{name}")
    public ResponseEntity<?> getNameStartingWithColloabrator(@PathVariable String name) {
        if (name.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Colloabrator Not Found");
        }

        List<Collaborator> collaborators = collaboratorService.findByNameCollaboratorStartingWith(name);
        return ResponseEntity.ok(collaborators);
    }

    @PostMapping
    public ResponseEntity<String> createCollaborator(@RequestBody Collaborator collaborator) {
        collaboratorRepository.save(collaborator);
        return ResponseEntity.ok("Collaborator created");
    }

    @PatchMapping("/{collaborator_id}")
    public ResponseEntity<String> partialUpdateCollaborator(@PathVariable Long collaborator_id, @RequestBody CollaboratorDTO collaboratorDTO) {
        Optional<Collaborator> optCollaborator = collaboratorRepository.findById(collaborator_id);
        if (!optCollaborator.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Colloabrator not found");
        }

        Collaborator collaborators = optCollaborator.get();
        serviceMapper.updateCollaboratorFromDto(collaboratorDTO, collaborators);
        collaboratorRepository.save(collaborators);
        return ResponseEntity.ok("Collaborator updated");
    }

    @DeleteMapping("/{collaborator_id}")
    public ResponseEntity<String> deleteCollaborator(@PathVariable Long collaborator_id) {
        if (!collaboratorRepository.existsById(collaborator_id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Collaborator not found");
        }

        collaboratorRepository.deleteById(collaborator_id);
        return ResponseEntity.ok("Collaborator deleted");
    }
}
