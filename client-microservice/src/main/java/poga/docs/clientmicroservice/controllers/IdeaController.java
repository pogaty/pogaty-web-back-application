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
import poga.docs.clientmicroservice.models.Idea;
import poga.docs.clientmicroservice.models.IdeaDTO;
import poga.docs.clientmicroservice.repositories.IdeaRepository;
import poga.docs.clientmicroservice.services.IdeaService;

@RestController
@RequestMapping("/ideas")
public class IdeaController {
    private final IdeaService ideaService;
    private final IdeaRepository ideaRepository;
    private final ServiceMapper serviceMapper;

    @Autowired
    IdeaController(IdeaService ideaService, IdeaRepository ideaRepository,ServiceMapper serviceMapper) {
        this.ideaService = ideaService;
        this.ideaRepository = ideaRepository;
        this.serviceMapper = serviceMapper;
    }

    // i dont khow
    @GetMapping
    public ResponseEntity<List<Idea>> getAllIdea() {
        List<Idea> idea = ideaService.findAllIdea();
        return ResponseEntity.ok(idea);
    }

    // Select idea By idea_id for show idea
    @GetMapping("/{idea_id}")
    public ResponseEntity getAllemployeeById(@PathVariable long idea_id) {
        Optional<Idea> optIdea = ideaRepository.findById(idea_id);
        
        // check if id exists in db
        if (!optIdea.isPresent()) {
            // return error message 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Idea Not Found");
        
        }
        Idea emp = optIdea.get();
        return ResponseEntity.ok(emp);
    }

    // 
    @GetMapping("/{ideaHeader}")
    public ResponseEntity<?> getUsernameIdea(@PathVariable String ideaHeader) {
        if (ideaHeader.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ideaHeader Not Found");
        }

        Idea idea = ideaService.findByHeaderIdea(ideaHeader);
        return ResponseEntity.ok(idea);
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
