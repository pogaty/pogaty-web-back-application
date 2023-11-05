package poga.docs.clientmicroservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poga.docs.clientmicroservice.models.Idea;
import poga.docs.clientmicroservice.models.Participant;
import poga.docs.clientmicroservice.repositories.ParticipantRepository;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    @Autowired
    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public List<Participant> findAllPaticipant() {
        return this.participantRepository.findAll();
    }

    public List<Participant> findByRolePaticipant(String role) {
        return this.participantRepository.findByRole(role);
    }

    public List<Participant> findByRolePaticipantStartingWith(String prefix) {
        return this.participantRepository.findByRoleStartingWith(prefix);
    }

    public Optional<Participant> findByParticipantId(Long id) {
        return this.participantRepository.findById(id);
    }

    public Optional<Participant> findByClientIdAndIdeaId(Long client_id, Long idea_id) {
        return this.participantRepository.findByClientIdAndIdeaid(client_id, idea_id);
    }

    public List<Idea> findIdeasByClientId(String username) {
        return this.participantRepository.findIdeaByClientId(username);
    }
}
