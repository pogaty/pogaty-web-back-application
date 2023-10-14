package poga.docs.partnermicroservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poga.docs.partnermicroservice.models.Collaborator;
import poga.docs.partnermicroservice.repositories.CollaboratorRepository;

@Service
public class CollaboratorService {
    
    private final CollaboratorRepository collaboratorRepository;

    @Autowired
    CollaboratorService( CollaboratorRepository collaboratorRepository) {
        this.collaboratorRepository = collaboratorRepository;
    }

    public List<Collaborator> findAllCollaborators() {
        return this.collaboratorRepository.findAll();
    }
}
