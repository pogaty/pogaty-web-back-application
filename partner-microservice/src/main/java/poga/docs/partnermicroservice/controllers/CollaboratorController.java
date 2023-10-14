package poga.docs.partnermicroservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poga.docs.partnermicroservice.services.CollaboratorService;

@RestController
@RequestMapping("/collabs")
public class CollaboratorController {
    
    private final CollaboratorService collaboratorService;

    @Autowired
    CollaboratorController(CollaboratorService collaboratorService) {
        this.collaboratorService = collaboratorService;
    }

    @GetMapping()
    public ResponseEntity<?> getAllCollaborators() {
        return ResponseEntity.ok(collaboratorService.findAllCollaborators());
    }
}
