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

import poga.docs.clientmicroservice.ClientMapper;
import poga.docs.clientmicroservice.models.Idea;
import poga.docs.clientmicroservice.models.IdeaDTO;
import poga.docs.clientmicroservice.repositories.IdeaRepository;
import poga.docs.clientmicroservice.services.IdeaService;

@RestController
@RequestMapping("/idea")
public class IdeaController {
    private final IdeaService ideaService;
    private final IdeaRepository ideaRepository;
    private final ClientMapper clientMapper;

    @Autowired
    IdeaController(IdeaService ideaService, IdeaRepository ideaRepository,ClientMapper clientMapper) {
        this.ideaService = ideaService;
        this.ideaRepository = ideaRepository;
        this.clientMapper = clientMapper;
    }

    @GetMapping
    public ResponseEntity<List<Idea>> getAllIdea() {
        List<Idea> idea = ideaService.findAllIdea();
        return ResponseEntity.ok(idea);
    }

    @GetMapping("/search/{ideaHeader}")
    public ResponseEntity<?> getUsernameIdea(@PathVariable String ideaHeader) {
        if (ideaHeader.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Idea Not Found");
        }

        List<Idea> idea = ideaService.findByHeaderIdea(ideaHeader);
        return ResponseEntity.ok(idea);
    }

    @GetMapping("/searchStarting/{ideaHeader}")
    public ResponseEntity<?> getUsernameStartingWithIdea(@PathVariable String ideaHeader) {
        if (ideaHeader.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Idea Not Found");
        }

        List<Idea> idea = ideaService.findIdeaByHeaderIdeaStartingWith(ideaHeader);
        return ResponseEntity.ok(idea);
    }

    @PostMapping
    public ResponseEntity<String> createIdea(@RequestBody Idea idea) {
        ideaRepository.save(idea);
        return ResponseEntity.ok("Idea created");
    }

    @PutMapping("/{idea_id}")
    public ResponseEntity<String> updateIdea(@PathVariable Long idea_id, @RequestBody Idea idea) {
        if (!ideaRepository.existsById(idea_id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Idea not found");
        }
        
        idea.setIdea_id(idea_id); // Ensure the idea_id is set
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

    @PatchMapping("/{idea_id}")
    public ResponseEntity<String> partialUpdateIdea(@PathVariable Long idea_id, @RequestBody IdeaDTO ideaDTO) {
        Optional<Idea> optIdea = ideaRepository.findById(idea_id);
        if (!optIdea.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Idea not found");
        }

        Idea idea = optIdea.get();
        clientMapper.updateIdeaFromDto(ideaDTO, idea);
        ideaRepository.save(idea);
        return ResponseEntity.ok("Idea updated");
    }
}
