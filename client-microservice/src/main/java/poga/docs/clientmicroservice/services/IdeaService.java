package poga.docs.clientmicroservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poga.docs.clientmicroservice.models.Idea;
import poga.docs.clientmicroservice.repositories.IdeaRepository;

@Service
public class IdeaService {
    private final IdeaRepository ideaRepository;

    @Autowired
    public IdeaService(IdeaRepository ideaRepository){
        this.ideaRepository = ideaRepository;
    }

    public List<Idea> findAllIdea() {
        return this.ideaRepository.findAll();
    }
    
    public Idea findByHeaderIdea(String ideaHeader) {
        return this.ideaRepository.findByIdeaHeader(ideaHeader);
    }

    public List<Idea> findByHeaderIdeaStartingWith(String prefix) {
        return this.ideaRepository.findByIdeaHeaderStartingWith(prefix);
    }
}
